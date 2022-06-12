package com.jdcm.testserviinfoapp.ui.movies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdcm.testserviinfoapp.ui.movies.ui.data.model.MoviesDetailModel
import com.jdcm.testserviinfoapp.ui.movies.ui.domain.GetMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetail : GetMovieDetail )  : ViewModel() {
    val movieDetailModel = MutableLiveData<MoviesDetailModel?>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(apiKey : String, movieId : Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMovieDetail(apiKey,movieId)

            if (result != null) {
                movieDetailModel.postValue(result)
                isLoading.postValue(false)
            }else{
                movieDetailModel.postValue(null)
                isLoading.postValue(false)
            }
        }

    }}