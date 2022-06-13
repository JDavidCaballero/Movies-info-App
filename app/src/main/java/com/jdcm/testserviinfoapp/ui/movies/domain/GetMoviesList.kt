package com.jdcm.testserviinfoapp.ui.movies.domain

import com.jdcm.testserviinfoapp.ui.movies.data.MoviesRepository
import com.jdcm.testserviinfoapp.ui.movies.data.database.entities.toDataBase
import com.jdcm.testserviinfoapp.ui.movies.domain.model.Movies
import javax.inject.Inject

class GetMoviesList @Inject constructor(
    private val repository: MoviesRepository
) {

    suspend operator fun invoke(apiKey: String): List<Movies?> {

        val moviesInfo = repository.getMoviesFromApi(apiKey)
        //Switch between Api and DB depending of the response of the api
        return if (!moviesInfo.isNullOrEmpty()) {
            repository.clearMoviesInfo()
            repository.insertMoviesInfo(moviesInfo.map { it!!.toDataBase() })
            moviesInfo
        } else {
            repository.getAllMoviesInfoFromDataBase()
        }

    }


}