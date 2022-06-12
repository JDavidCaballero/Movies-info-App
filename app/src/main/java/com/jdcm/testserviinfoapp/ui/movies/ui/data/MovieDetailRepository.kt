package com.jdcm.testserviinfoapp.ui.movies.ui.data


import com.jdcm.testserviinfoapp.ui.movies.ui.data.model.MoviesDetailModel
import com.jdcm.testserviinfoapp.ui.movies.ui.data.network.MovieDetailService
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val api: MovieDetailService?,
) {

    suspend fun getMoviesFromApi(apiKey : String, movieId :Int): MoviesDetailModel? {

        return if (api!!.getMovieDetail(apiKey,movieId) != null) {
            val response: MoviesDetailModel? = api.getMovieDetail(apiKey,movieId)
            response!!.copy()
        }else{
            null
        }
    }

}