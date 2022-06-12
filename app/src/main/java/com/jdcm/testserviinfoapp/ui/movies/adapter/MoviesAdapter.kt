package com.jdcm.testserviinfoapp.ui.movies.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jdcm.testserviinfoapp.R
import com.jdcm.testserviinfoapp.ui.movies.data.model.MoviesDataModel
import com.jdcm.testserviinfoapp.ui.movies.domain.model.Movies

class MoviesAdapter(
    private var movieList: List<Movies?>,
    private val context: Activity,
) : RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MoviesViewHolder(
            layoutInflater.inflate(
                R.layout.list_item_movies,
                parent,
                false
            )
        )
    }

    /**
     * get Item Count
     */
    override fun getItemCount(): Int = movieList.size


    /**
     * on Bind ViewHolder
     */
    override fun onBindViewHolder(holderReception: MoviesViewHolder, position: Int) {
        val item: Movies? = movieList[position]
        holderReception.bind(item,context)



    }


}