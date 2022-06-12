package com.jdcm.testserviinfoapp.ui.movies.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jdcm.testserviinfoapp.ui.movies.data.database.entities.MoviesListEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies_list_table ORDER BY title DESC")
    suspend fun getAllMoviesInfo():List<MoviesListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(personsInfo:List<MoviesListEntity>)

    @Query("DELETE FROM movies_list_table")
    suspend fun deleteAllMoviesInfo()
}