package com.jdcm.testserviinfoapp.ui.movies

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jdcm.testserviinfoapp.R
import com.jdcm.testserviinfoapp.databinding.MoviesFragmentBinding
import com.jdcm.testserviinfoapp.ui.movies.adapter.MoviesAdapter
import com.jdcm.testserviinfoapp.ui.movies.domain.model.Movies
import com.jdcm.testserviinfoapp.utils.Constants.Companion.APIKEY
import com.jdcm.testserviinfoapp.utils.recycler.RecyclerItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var binding: MoviesFragmentBinding
    private val viewModel: MoviesViewModel by viewModels()
    private var moviesList = mutableListOf<Movies?>()
    private var moviesListBackup = mutableListOf<Movies?>()
    private var moviesListFiltered = mutableListOf<Movies?>()
    private var adapter: MoviesAdapter? = null
    private val navController by lazy { Navigation.findNavController(binding.root) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MoviesFragmentBinding.inflate(inflater, container, false)
        initMoviesViewModel()

        binding.noDataLayout.btnNoContent.setOnClickListener {
            //If theres no connection this button will go up and the user can retry the call to the Api
            viewModel.onCreate(APIKEY)
        }

        return binding.root
    }


    private fun searchMoviesTitle() {

        binding.searchEd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s != null && s.toString().isNotEmpty()) {
                    searchFilter(s.toString())
                } else {
                    binding.searchEd.clearFocus()
                    binding.movies.requestFocus()
                    updateAdapter(ArrayList(moviesList))
                }
            }
        })
    }

    private fun searchFilter(filterText: String) {
        //Took a list and get in there all the data that matches the word that the user put
        moviesListFiltered.clear()
        moviesList.forEach { item ->
            if (item!!.title.lowercase(Locale.getDefault())
                    .contains(filterText.lowercase(Locale.getDefault()))
            ) {
                moviesListFiltered.add(item)
            }
        }
        updateAdapter(ArrayList(moviesListFiltered))
    }


    private fun fillMoviesInfo(moviesData: Collection<Movies?>) {
        moviesList.clear()
        moviesList.addAll(moviesData)
        moviesListBackup.clear()
        moviesListBackup.addAll(moviesData)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateAdapter(data: Collection<Movies?>) {
        //To update the original list that will be displayed on screen with adapter
        moviesListBackup.clear()
        moviesListBackup.addAll(data)
        adapter!!.notifyDataSetChanged()
    }

    private fun initMoviesViewModel() {
        //If there was a token to took from the device, we pass it to the Api as parameter for an authorization Api key is an example
        viewModel.onCreate(APIKEY)
        //Set observer for the data that's gonna come from the db or internet if there are or not Internet connection
        viewModel.moviesModel.observe(viewLifecycleOwner) { MoviesApi ->

            if (!MoviesApi.isNullOrEmpty()) {
                binding.noDataLayout.root.visibility = View.GONE

                fillMoviesInfo(MoviesApi.toMutableList())
                initRecyclerView()
                if (adapter != null)
                    searchMoviesTitle()
            } else {
                binding.noDataLayout.root.visibility = View.VISIBLE
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_available_information),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        //Set the loading
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }


    private fun initRecyclerView() {
        binding.moviesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.moviesRecyclerView.hasFixedSize()
        binding.moviesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        adapter = MoviesAdapter(moviesListBackup, requireActivity())
        binding.moviesRecyclerView.adapter = adapter
        binding.moviesRecyclerView.addOnItemTouchListener(RecyclerItemClickListener(
            requireContext(),
            binding.moviesRecyclerView,
            object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    val action: NavDirections =
                        MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
                            moviesListBackup[position]!!.poster_path,
                            moviesListBackup[position]!!.movieId,
                        )
                    navController.navigate(action)
                }

                override fun onLongItemClick(view: View?, position: Int) {}
            }

        ))
    }


}