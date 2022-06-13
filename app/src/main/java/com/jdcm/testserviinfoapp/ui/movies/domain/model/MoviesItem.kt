package com.jdcm.testserviinfoapp.ui.movies.domain.model

import com.jdcm.testserviinfoapp.ui.movies.data.database.entities.MoviesListEntity
import com.jdcm.testserviinfoapp.ui.movies.data.model.MoviesListInfo

/* This class is the generic class of the data for code to understand that the data of the API and de DB will be the same independent of his type */
data class Movies(

    var movieId: Int,
    var isPlus18: Boolean,
    var backdropPath: String,
    var originalLanguage: String,
    var poster_path: String,
    var title: String,
    var voteAverage: String,
    var releaseDate: String


)

fun MoviesListInfo.toDomain() = Movies(movieId, isPlus18, backdropPath, originalLanguage,poster_path,title,voteAverage,releaseDate)

fun MoviesListEntity.toDomain() = Movies(movieId, isPlus18, backdropPath!!, originalLanguage,poster_path,title,voteAverage,releaseDate)

