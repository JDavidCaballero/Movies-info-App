package com.jdcm.testserviinfoapp.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdcm.testserviinfoapp.ui.movies.domain.GetMoviesList
import com.jdcm.testserviinfoapp.ui.movies.domain.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesModel: GetMoviesList) : ViewModel() {

    val moviesModel = MutableLiveData<List<Movies?>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(apiKey: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMoviesModel(apiKey)

            //Putting the values for the main fragment
            moviesModel.postValue(result)
            isLoading.postValue(false)

        }

    }

}