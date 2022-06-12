package com.jdcm.testserviinfoapp.ui.movies.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jdcm.testserviinfoapp.ui.movies.domain.model.Movies

@Entity(tableName = "movies_list_table")
data class MoviesListEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movieId") val movieId: Int = 0,
    @ColumnInfo(name = "isPlus18") var isPlus18: Boolean,
    @ColumnInfo(name = "backdropPath") var backdropPath: String?,
    @ColumnInfo(name = "originalLanguage") var originalLanguage: String,
    @ColumnInfo(name = "poster_path") var poster_path: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "voteAverage") var voteAverage: String,
    @ColumnInfo(name = "releaseDate") var releaseDate: String
)

fun Movies.toDataBase() = MoviesListEntity(
    movieId,
    isPlus18,
    backdropPath,
    originalLanguage,
    poster_path,
    title,
    voteAverage,
    releaseDate
)