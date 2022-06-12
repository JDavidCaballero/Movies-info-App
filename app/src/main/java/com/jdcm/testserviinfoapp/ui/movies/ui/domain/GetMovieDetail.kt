package com.jdcm.testserviinfoapp.ui.movies.ui.domain

import com.jdcm.testserviinfoapp.ui.movies.ui.data.MovieDetailRepository
import com.jdcm.testserviinfoapp.ui.movies.ui.data.model.MoviesDetailModel
import javax.inject.Inject

class GetMovieDetail @Inject constructor(
    private val repository : MovieDetailRepository
) {

    suspend operator fun invoke(apiKey: String, movieId: Int): MoviesDetailModel? {

        return repository.getMoviesFromApi(apiKey, movieId)

    }


}