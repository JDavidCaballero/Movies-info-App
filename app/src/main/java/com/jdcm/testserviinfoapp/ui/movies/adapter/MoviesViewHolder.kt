package com.jdcm.testserviinfoapp.ui.movies.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jdcm.testserviinfoapp.R
import com.jdcm.testserviinfoapp.databinding.ListItemMoviesBinding
import com.jdcm.testserviinfoapp.ui.movies.domain.model.Movies
import com.jdcm.testserviinfoapp.utils.Constants.Companion.URL_FOR_IMAGES


class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ListItemMoviesBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun bind(
        movieInfo: Movies?,
        context: Activity,
    ) {

        Glide.with(context)
            .load("$URL_FOR_IMAGES${movieInfo!!.backdropPath}")
            .placeholder(R.drawable.ic_baseline_video_settings)
            .into(binding.imMoviePoster)

        binding.movieTitleTv.text =   movieInfo.title
        binding.qualificationTv.text ="${context.getString(R.string.movie_qualification_tv)} ${movieInfo.voteAverage}"
        binding.agedRatedTv.text = if (movieInfo.isPlus18) {
            context.getString(R.string.plus_18_txt)
        } else {
            context.getString(R.string.for_everyone_txt)
        }
    }
}