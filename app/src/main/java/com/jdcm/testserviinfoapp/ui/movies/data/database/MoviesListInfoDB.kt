package com.jdcm.testserviinfoapp.ui.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jdcm.testserviinfoapp.ui.movies.data.database.dao.MoviesDao
import com.jdcm.testserviinfoapp.ui.movies.data.database.entities.MoviesListEntity

@Database(entities = [MoviesListEntity::class], version = 1)
abstract class MoviesListInfoDB:RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

}