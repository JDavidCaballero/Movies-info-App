package com.jdcm.testserviinfoapp.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jdcm.testserviinfoapp.ui.movies.domain.GetMoviesList
import com.jdcm.testserviinfoapp.ui.movies.domain.model.Movies
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
class MoviesViewModelTest {

    @RelaxedMockK
    lateinit var getMoviesList: GetMoviesList

    private lateinit var moviesViewModel: MoviesViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        moviesViewModel = MoviesViewModel(getMoviesList)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel Is Created Get All Movies List And Set It To the list `() = runTest {

        val movieList = listOf(
            Movies(
                1,
                false,
                "9DSASDS.jpg",
                "en",
                "9DSASDS.jpg",
                "Pelicula titulo",
                "1.2",
                "2022/12/06"
            ),
            Movies(
                2,
                false,
                "9DSASDS.jpg",
                "en",
                "9DSASDS.jpg",
                "Pelicula titulo",
                "1.2",
                "2022/12/06"
            )
        )

        //Given
        coEvery { getMoviesList(APIKEY) } returns movieList
        //When
        moviesViewModel.onCreate(APIKEY)
        //Then
        assert(moviesViewModel.moviesModel.value == movieList)
    }

    @Test
    fun `If Api Fails And Returns exception or emptyList Return lastOne and not cause and exception that stops app`() =
        runTest {
            //Given
            coEvery { getMoviesList(APIKEY) } returns emptyList()
            //when
            moviesViewModel.onCreate(APIKEY)
            //Then
            assert(moviesViewModel.moviesModel.value.isNullOrEmpty())
        }

}