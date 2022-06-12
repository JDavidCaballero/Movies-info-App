package com.jdcm.testserviinfoapp.ui.movies.ui.data.network

import com.jdcm.testserviinfoapp.ui.movies.ui.data.model.MoviesDetailModel
import com.jdcm.testserviinfoapp.utils.Constants.Companion.MOVIES_DETAIL_URL
import com.jdcm.testserviinfoapp.utils.Constants.Companion.MOVIES_LIST_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MovieDetailApiClient {
    @GET(MOVIES_DETAIL_URL)
    suspend fun getMoviesDetail(
        @Header("api_key") apiKey :String,
        @Path("movie_id") movieId :Int
    ): Response<MoviesDetailModel?>
}