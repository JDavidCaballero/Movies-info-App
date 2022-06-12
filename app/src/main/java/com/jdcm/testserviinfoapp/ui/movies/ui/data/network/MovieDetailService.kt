package com.jdcm.testserviinfoapp.ui.movies.ui.data.network

import com.jdcm.testserviinfoapp.ui.movies.ui.data.model.MoviesDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailService @Inject constructor(private val api: MovieDetailApiClient) {

    suspend fun getMovieDetail(apiKey: String, movieId: Int): MoviesDetailModel? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getMoviesDetail(apiKey, movieId)
                response.body()
            } catch (e: Exception) {
                return@withContext null
            }
        }
    }

}