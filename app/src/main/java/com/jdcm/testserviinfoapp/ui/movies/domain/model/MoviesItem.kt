package com.jdcm.testserviinfoapp.ui.movies.domain.model

import com.jdcm.testserviinfoapp.ui.movies.data.database.entities.MoviesListEntity
import com.jdcm.testserviinfoapp.ui.movies.data.model.MoviesListInfo


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

