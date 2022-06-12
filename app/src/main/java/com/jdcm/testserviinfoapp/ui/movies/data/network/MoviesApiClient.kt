package com.jdcm.testserviinfoapp.ui.movies.data.network

import com.jdcm.testserviinfoapp.ui.movies.data.model.MoviesDataModel
import com.jdcm.testserviinfoapp.utils.Constants.Companion.MOVIES_LIST_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MoviesApiClient {
    @GET(MOVIES_LIST_URL)
    suspend fun getMoviesData(
        @Header("api_key") apiKey :String
    ): Response<MoviesDataModel?>
}