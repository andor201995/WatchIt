package com.andor.watchit.core.framework

import androidx.sqlite.db.SimpleSQLiteQuery
import com.andor.watchit.core.framework.db.MovieDao
import com.andor.watchit.core.framework.db.MovieEntity
import com.andor.watchit.repository.MovieDataSource
import com.andor.watchit.usecase.common.model.GeneralMovie

class RoomMovieDataSource(private val movieDao: MovieDao) : MovieDataSource {

    override suspend fun add(generalMovie: GeneralMovie) =
        movieDao.addMovieEntity(MovieEntity.fromGeneralMovie(generalMovie))

    override suspend fun addAll(generalMovieList: List<GeneralMovie>) =
        movieDao.addAllMovieEntity(generalMovieList.map { MovieEntity.fromGeneralMovie(it) })

    override suspend fun get(movieId: Double) = movieDao.getMovieEntity(movieId)?.toGeneralMovie()

    override suspend fun getAll(): List<GeneralMovie> =
        movieDao.getAllMovieEntity().map { it.toGeneralMovie() }

    override suspend fun remove(generalMovie: GeneralMovie) =
        movieDao.deleteMovie(MovieEntity.fromGeneralMovie(generalMovie))

    override suspend fun getpage(pageNumber: Int, pageSize: Int): List<GeneralMovie> =
        movieDao.getPagedMovie(pageNumber, pageSize).map { it.toGeneralMovie() }

    override suspend fun getCachedMovieCount(): Int = movieDao.getCount()

    override suspend fun getMoviesByName(query: String, pageNumber: Int): List<GeneralMovie> {
        val queryArgs = query.split("+")

        val queryString =
            getQueryString(queryArgs).append("\nORDER BY rating DESC LIMIT 20 OFFSET ($pageNumber-1)*20")

        val simpleSQLiteQuery = SimpleSQLiteQuery(queryString.toString())
        return movieDao.getMovieEntityByQuery(simpleSQLiteQuery).map { it.toGeneralMovie() }
    }

    override suspend fun getSearchMovieCount(query: String, pageNumber: Int): Int {
        val queryArgs = query.split("+")
        val queryString =
            getQueryString(queryArgs).insert(0, "SELECT COUNT(movie_id) FROM(\n").append("\n)")
        val simpleSQLiteQuery = SimpleSQLiteQuery(queryString.toString())
        return movieDao.getSearchMovieCount(simpleSQLiteQuery)
    }

    private fun getQueryString(
        query: List<String>
    ): StringBuilder {
        val queryString =
            StringBuilder("SELECT * FROM movie")

        if (query.size > 0) {
            queryString.append("\nWHERE original_title like '%${query[0]}%'")
        }

        if (query.size > 1) {
            (1 until query.size).forEach { index ->
                queryString.append("\nOR original_title like '%${query[index]}%' ")
            }
        }
        return queryString
    }
}
