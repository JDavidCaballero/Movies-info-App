package com.jdcm.testserviinfoapp.ui.movies.data.model

import com.google.gson.annotations.SerializedName

data class MoviesDataModel(
    @SerializedName("results") var results: ArrayList<MoviesListInfo>,
)

data class MoviesListInfo(
    @SerializedName("id") var movieId: Int,
    @SerializedName("adult") var isPlus18: Boolean,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("poster_path") var poster_path: String,
    @SerializedName("title") var title: String,
    @SerializedName("vote_average") var voteAverage: String,
    @SerializedName("release_date") var releaseDate: String
)