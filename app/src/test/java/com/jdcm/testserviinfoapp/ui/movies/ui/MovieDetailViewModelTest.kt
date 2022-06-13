package com.jdcm.testserviinfoapp.ui.movies.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jdcm.testserviinfoapp.ui.movies.ui.data.model.MoviesDetailModel
import com.jdcm.testserviinfoapp.ui.movies.ui.domain.GetMovieDetail
import com.jdcm.testserviinfoapp.utils.Constants.Companion.APIKEY
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @RelaxedMockK
    private lateinit var getMovieDetail: GetMovieDetail

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        movieDetailViewModel = MovieDetailViewModel(getMovieDetail)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `If the Api Call returns null then set the object to null because theres no DB for backup to display data`() =
        runTest {
            //Given
            coEvery { getMovieDetail(APIKEY, 1) } returns null
            //When
            movieDetailViewModel.onCreate(APIKEY, 1)
            //Then
            assert(movieDetailViewModel.movieDetailModel.value == null)
            assert(movieDetailViewModel.isLoading.value == false)
        }

    @Test
    fun `If the Api Call returns Object then set the object to the mutableObject`() =
        runTest {
            val objectFromApi = MoviesDetailModel(1,"titulo",true,"dsad322.JPG","3.2","en cartelera","es","2022/12/06",10000,200000,"resumen","Descripcion Gigante")

            //Given
            coEvery { getMovieDetail(APIKEY, 1) } returns objectFromApi
            //When
            movieDetailViewModel.onCreate(APIKEY, 1)
            //Then
            assert(movieDetailViewModel.movieDetailModel.value == objectFromApi)
            assert(movieDetailViewModel.isLoading.value == false)
        }

}