package com.jdcm.testserviinfoapp.ui.movies.ui.domain

import com.jdcm.testserviinfoapp.ui.movies.ui.data.MovieDetailRepository
import com.jdcm.testserviinfoapp.ui.movies.ui.data.model.MoviesDetailModel
import com.jdcm.testserviinfoapp.utils.Constants.Companion.APIKEY
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieDetailTest {


    @RelaxedMockK
    private lateinit var movieDetailRepository: MovieDetailRepository

    private lateinit var getMoviesDetail: GetMovieDetail

    @Before
    fun onBefore() {

        MockKAnnotations.init(this)
        getMoviesDetail = GetMovieDetail(movieDetailRepository)
    }

    @Test
    fun `if The Api Doesnt Return Data Repository Must Return Null`() = runBlocking {
        //Given
        coEvery { movieDetailRepository.getMoviesFromApi(APIKEY, 1) } returns null
        //When
        val result = getMoviesDetail
        //Then
        assert(result.invoke(APIKEY, 1) == null)
    }

    @Test
    fun `If the Api SuccessFull Retrieve Data Then Response Must Be The Object`() = runBlocking {

        val movieDetail = MoviesDetailModel(1,"Titulo", false,"9DSASDS.jpg","6.2","En cartelera","en","2022/12/06",20000,10000,"Resumen corto","Basicamente una descripción larga de la peliculda")

        //Given
        coEvery { movieDetailRepository.getMoviesFromApi(APIKEY,1) } returns movieDetail

        //When
        val result = getMoviesDetail.invoke(APIKEY,1)

        //Then
        coVerify(exactly = 1) { movieDetailRepository.getMoviesFromApi(APIKEY,1) }
        assert(result == movieDetail)
    }


}