package com.jdcm.testserviinfoapp.ui.movies.data

import com.jdcm.testserviinfoapp.ui.movies.data.database.dao.MoviesDao
import com.jdcm.testserviinfoapp.ui.movies.data.database.entities.MoviesListEntity
import com.jdcm.testserviinfoapp.ui.movies.data.model.MoviesListInfo
import com.jdcm.testserviinfoapp.ui.movies.data.network.MoviesService
import com.jdcm.testserviinfoapp.ui.movies.domain.model.Movies
import com.jdcm.testserviinfoapp.ui.movies.domain.model.toDomain
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val api: MoviesService?,
    private val moviesDao: MoviesDao
) {

    suspend fun getMoviesFromApi(apiKey : String): List<Movies?>? {

        return if (api!!.getMovies(apiKey) != null) {
            val response: List<MoviesListInfo?> = api.getMovies(apiKey)!!.results
            response.map { it!!.toDomain() }
        }else{ null }
    }

    suspend fun getAllMoviesInfoFromDataBase(): List<Movies> {

        val response = moviesDao.getAllMoviesInfo()
        return response.map { it.toDomain() }

    }

    suspend fun insertMoviesInfo(peopleInfo: List<MoviesListEntity>) {
        moviesDao.insertAll(peopleInfo)
    }

    suspend fun clearMoviesInfo() {
        moviesDao.deleteAllMoviesInfo()
    }


}