package com.andor.watchit.repository.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.andor.watchit.repository.entity.MovieDbContract
import com.andor.watchit.repository.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    suspend fun addMovieEntity(movieEntity: MovieEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addAllMovieEntity(movieEntityList: List<MovieEntity>)

    @Query("SELECT * FROM ${MovieDbContract.TABLE_NAME} WHERE ${MovieDbContract.MOVIE_ID}=:movieId")
    suspend fun getMovieEntity(movieId: Double): MovieEntity?

    @Query("SELECT * FROM ${MovieDbContract.TABLE_NAME} ORDER BY ${MovieDbContract.RATING}")
    suspend fun getAllMovieEntity(): List<MovieEntity>

    @Query(
        """SELECT * FROM ${MovieDbContract.TABLE_NAME} ORDER BY ${MovieDbContract.RATING} DESC
                LIMIT :pageSize OFFSET (:pageNumber - 1)*:pageSize"""
    )
    suspend fun getPagedMovie(pageNumber: Int, pageSize: Int): List<MovieEntity>

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT COUNT(${MovieDbContract.MOVIE_ID}) FROM ${MovieDbContract.TABLE_NAME}")
    suspend fun getCount(): Int

    @RawQuery
    suspend fun getMovieEntityByQuery(query: SimpleSQLiteQuery): List<MovieEntity>

    @RawQuery
    suspend fun getSearchMovieCount(query: SimpleSQLiteQuery): Int
}
