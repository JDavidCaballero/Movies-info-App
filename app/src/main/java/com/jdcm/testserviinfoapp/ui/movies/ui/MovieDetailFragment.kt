package com.jdcm.testserviinfoapp.ui.movies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.jdcm.testserviinfoapp.R
import com.jdcm.testserviinfoapp.databinding.MovieDetailFragmentBinding
import com.jdcm.testserviinfoapp.ui.movies.ui.data.model.MoviesDetailModel
import com.jdcm.testserviinfoapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    private var success = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        initMoviesViewModel()
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.noDataLayout.btnNoContent.setOnClickListener {
            //If theres no connection this button will go up and the user can retry the call to the Api
            viewModel.onCreate(
                Constants.APIKEY,
                MovieDetailFragmentArgs.fromBundle(requireArguments()).movieId
            )
        }
        return binding.root
    }

    private fun initMoviesViewModel() {
        viewModel.onCreate(
            Constants.APIKEY,
            MovieDetailFragmentArgs.fromBundle(requireArguments()).movieId
        )
        //Set observer for the data that's gonna come from the db or internet if there are or not Internet connection
        viewModel.movieDetailModel.observe(viewLifecycleOwner) { MoviesApi ->
            if (MoviesApi != null) {
                success = true
                binding.noDataLayout.root.visibility = View.GONE

                setUiInfo(MoviesApi)
            } else {
                success = false
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
            binding.imgSlider.isVisible = success
            binding.movieDetailTitleTv.isVisible = success
            binding.scrollView.isVisible = success
        }
    }

    private fun setUiInfo(moviesApi: MoviesDetailModel?) {

        binding.movieDetailTitleTv.text = moviesApi!!.title
        //Set image Slider with the front photo and the poster photo of the movie
        setImageSlider(
            moviesApi.backdropPath,
            MovieDetailFragmentArgs.fromBundle(requireArguments()).posterPhoto
        )
        //Set all textView Text
        val textCategory = "${getText(R.string.age_rating_txt)} ${
            if (moviesApi.isPlus18) {
                getString(R.string.plus_18_txt)
            } else {
                getString(R.string.for_everyone_txt)
            }
        }"
        val textRate = "${getText(R.string.movie_qualification_tv)} ${moviesApi.voteAverage}"
        val textStatus = "${getText(R.string.status_txt)} ${moviesApi.status} "
        val textLanguage =
            "${getText(R.string.original_language_txt)} ${moviesApi.originalLanguage}"
        val textReleaseDate = "${getString(R.string.release_date_txt)} ${moviesApi.releaseDate} "
        val textResume = "${getString(R.string.synopsis_txt)} ${moviesApi.tagline} "
        val textOverview = "${getString(R.string.resume_txt)} ${moviesApi.overview}"
        //Sett info in textViews
        binding.categoryTv.text = textCategory
        binding.rateTv.text = textRate
        binding.statusTv.text = textStatus
        binding.originalLanguageTv.text = textLanguage
        binding.releaseDateTv.text = textReleaseDate
        binding.taglineTv.text = textResume
        binding.overviewTv.text = textOverview
        //Set a function that put the int value to dollars format
        binding.budgetTv.moneyConverter(moviesApi.budget, true)
        binding.revenueTv.moneyConverter(moviesApi.revenue, false)
    }

    private fun TextView.moneyConverter(value: Int, isBudget : Boolean) {
        var currencyText = if (isBudget){

            "${getString(R.string.budget_txt) }   ${getString(R.string.dollar_sign)} ${
                NumberFormat.getNumberInstance(Locale.US).format(value)
            } ${getString(R.string.dollars_text)}"
        }else{
            "${getString(R.string.total_revenue_txt) }   ${getString(R.string.dollar_sign)} ${
                NumberFormat.getNumberInstance(Locale.US).format(value)
            } ${getString(R.string.dollars_text)}"
        }
        this.text = currencyText
    }

    private fun setImageSlider(backPhoto: String, frontPhoto: String) {
        val imageList = mutableListOf<SlideModel>()
        imageList.add(SlideModel("${Constants.URL_FOR_IMAGES}$backPhoto", ""))
        imageList.add(SlideModel("${Constants.URL_FOR_IMAGES}$frontPhoto", ""))
        binding.imgSlider.setImageList(imageList, ScaleTypes.FIT)
    }


}