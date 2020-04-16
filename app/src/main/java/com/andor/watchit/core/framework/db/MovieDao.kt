package com.andor.watchit.core.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    suspend fun addMovieEntity(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie WHERE movie_id=:movieId")
    suspend fun getMovieEntity(movieId: Double): MovieEntity?

    @Query("SELECT * FROM movie")
    suspend fun getAllMovieEntity(): List<MovieEntity>

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)
}