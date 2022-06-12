package com.jdcm.testserviinfoapp.di

import android.content.Context
import androidx.room.Room
import com.jdcm.testserviinfoapp.ui.movies.data.database.MoviesListInfoDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val MOVIES_DATA_BASE_NAME = "movies_List_dataBase"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, MoviesListInfoDB::class.java, MOVIES_DATA_BASE_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(db:MoviesListInfoDB) = db.getMoviesDao()

}