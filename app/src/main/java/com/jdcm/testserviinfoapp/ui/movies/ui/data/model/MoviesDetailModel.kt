package com.jdcm.testserviinfoapp.ui.movies.ui.data.model

import com.google.gson.annotations.SerializedName

data class MoviesDetailModel(


    @SerializedName("id") var movieId: Int,
    @SerializedName("title") var title: String,
    @SerializedName("adult") var isPlus18: Boolean,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("vote_average") var voteAverage: String,
    @SerializedName("status") var status: String,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("budget") var budget: Int,
    @SerializedName("revenue") var revenue: Int,
    @SerializedName("tagline") var tagline: String,
    @SerializedName("overview") var overview: String,

)