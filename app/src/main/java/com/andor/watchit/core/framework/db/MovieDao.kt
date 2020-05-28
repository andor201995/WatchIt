package com.andor.watchit.core.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    suspend fun addMovieEntity(movieEntity: MovieEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addAllMovieEntity(movieEntityList: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE movie_id=:movieId")
    suspend fun getMovieEntity(movieId: Double): MovieEntity?

    @Query("SELECT * FROM movie ORDER BY rating")
    suspend fun getAllMovieEntity(): List<MovieEntity>

    @Query("SELECT * FROM movie ORDER BY rating DESC LIMIT :pageSize OFFSET (:pageNumber-1)*:pageSize")
    suspend fun getPagedMovie(pageNumber: Int, pageSize: Int): List<MovieEntity>

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT COUNT(movie_id) FROM movie")
    suspend fun getCount(): Int

    @RawQuery
    suspend fun getMovieEntityByQuery(query: SimpleSQLiteQuery): List<MovieEntity>

    @RawQuery
    suspend fun getSearchMovieCount(query: SimpleSQLiteQuery): Int
}
