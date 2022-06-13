package com.jdcm.testserviinfoapp.ui.movies.domain

import com.jdcm.testserviinfoapp.ui.movies.data.MoviesRepository
import com.jdcm.testserviinfoapp.ui.movies.domain.model.Movies
import com.jdcm.testserviinfoapp.utils.Constants.Companion.APIKEY
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetMoviesListTest {

    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository

    lateinit var getMoviesList: GetMoviesList

    @Before
    fun onBefore() {

        MockKAnnotations.init(this)
        getMoviesList = GetMoviesList(moviesRepository)
    }

    @Test
    fun `if The Api Returns EmptyList Then Get Info From Data Base`() = runBlocking {

        //Given
        coEvery { moviesRepository.getMoviesFromApi(APIKEY) } returns emptyList()
        //When
        getMoviesList(APIKEY)
        //Then
        coVerify(exactly = 1) {
            moviesRepository.getAllMoviesInfoFromDataBase()
        }
        coVerify(exactly = 0) { moviesRepository.clearMoviesInfo()}
        coVerify(exactly =0) { moviesRepository.insertMoviesInfo(any())}
    }

    @Test
    fun `if The Api Returns null Or Doesnt Work Then Get Info From Data Base`() = runBlocking {

        //Given
        coEvery { moviesRepository.getMoviesFromApi(APIKEY) } returns null
        //When
        getMoviesList(APIKEY)
        //Then
        coVerify(exactly = 1) {
            moviesRepository.getAllMoviesInfoFromDataBase()
        }
        coVerify(exactly = 0) { moviesRepository.clearMoviesInfo()}
        coVerify(exactly =0) { moviesRepository.insertMoviesInfo(any())}
    }

    @Test
    fun `if There Are Internet Connection And Api Has Data Get All Values `() = runBlocking {

        val movieList = listOf(Movies(1,false,"9DSASDS.jpg","en","9DSASDS.jpg","Pelicula titulo", "1.2","2022/12/06"))

        //Given
        coEvery { moviesRepository.getMoviesFromApi(APIKEY) } returns movieList

        //When
        val response = getMoviesList(APIKEY)
        //Then
        coVerify(exactly = 1) { moviesRepository.clearMoviesInfo()}
        coVerify(exactly = 1) { moviesRepository.insertMoviesInfo(any())}
        coVerify(exactly = 0) { moviesRepository.getAllMoviesInfoFromDataBase()}
        assert(movieList == response)

    }

    @Test
    fun `if The DataBase Is Empty Return Empty List And Not Null `() = runBlocking {
        //Given
        coEvery { moviesRepository.getAllMoviesInfoFromDataBase() } returns emptyList()
        //When
        val response = getMoviesList(APIKEY)
        //Then
        assert(response.isNullOrEmpty())
    }


}