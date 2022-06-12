package com.jdcm.testserviinfoapp.utils

class Constants {

    companion object {

        //ApiKey
        const val APIKEY = "3cb28c6ba23d494a1661515897c8ca05"

        //Base url
        const val BASE_URL = "https://api.themoviedb.org/3/"

        //Get Methods
        const val MOVIES_LIST_URL =
            "movie/popular?api_key=3cb28c6ba23d494a1661515897c8ca05&language=es-ES"
        const val MOVIES_DETAIL_URL =
            "movie/{movie_id}?api_key=3cb28c6ba23d494a1661515897c8ca05&language=es-ES"
        //General Urls
        const val URL_FOR_IMAGES = "https://image.tmdb.org/t/p/w500"

    }

}