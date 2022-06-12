package com.jdcm.testserviinfoapp.ui.movies.data.network

import com.jdcm.testserviinfoapp.ui.movies.data.model.MoviesDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesService @Inject constructor(private val api: MoviesApiClient) {

    suspend fun getMovies(apiKey: String): MoviesDataModel? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getMoviesData(apiKey)
                response.body()
            } catch (e: Exception) {
                return@withContext null
            }
        }
    }

}