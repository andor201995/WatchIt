package com.andor.watchit.repository.movie

import androidx.sqlite.db.SimpleSQLiteQuery
import com.andor.watchit.repository.RepositoryUtils.constructSearchQuery
import com.andor.watchit.repository.entity.MovieDbContract
import com.andor.watchit.repository.entity.MovieEntity
import com.andor.watchit.usecase.common.model.GeneralMovie
import javax.inject.Inject

interface MovieDataSource {
    suspend fun add(generalMovie: GeneralMovie)
    suspend fun addAll(generalMovieList: List<GeneralMovie>)
    suspend fun get(movieId: Double): GeneralMovie?
    suspend fun getAll(): List<GeneralMovie>
    suspend fun remove(generalMovie: GeneralMovie)
    suspend fun getPage(pageNumber: Int, pageSize: Int = 20): List<GeneralMovie>
    suspend fun getCachedMovieCount(): Int
    suspend fun getMoviesByName(query: String, pageNumber: Int): List<GeneralMovie>
    suspend fun getSearchMovieCount(query: String, pageNumber: Int): Int
}

class MovieDataSourceImpl @Inject constructor(private val movieDao: MovieDao) :
    MovieDataSource {

    override suspend fun add(generalMovie: GeneralMovie) =
        movieDao.addMovieEntity(MovieEntity.fromGeneralMovie(generalMovie))

    override suspend fun addAll(generalMovieList: List<GeneralMovie>) =
        movieDao.addAllMovieEntity(generalMovieList.map { MovieEntity.fromGeneralMovie(it) })

    override suspend fun get(movieId: Double) = movieDao
        .getMovieEntity(movieId)?.toGeneralMovie()

    override suspend fun getAll(): List<GeneralMovie> =
        movieDao.getAllMovieEntity().map { it.toGeneralMovie() }

    override suspend fun remove(generalMovie: GeneralMovie) =
        movieDao.deleteMovie(MovieEntity.fromGeneralMovie(generalMovie))

    override suspend fun getPage(pageNumber: Int, pageSize: Int): List<GeneralMovie> =
        movieDao.getPagedMovie(pageNumber, pageSize).map { it.toGeneralMovie() }

    override suspend fun getCachedMovieCount(): Int = movieDao.getCount()

    override suspend fun getMoviesByName(query: String, pageNumber: Int): List<GeneralMovie> {
        val queryArgs = query.split("+")

        val queryString =
            constructSearchQuery(
                queryArgs,
                MovieDbContract.TABLE_NAME,
                MovieDbContract.ORIGINAL_TITLE
            ).append("\nORDER BY rating DESC LIMIT 20 OFFSET ($pageNumber-1)*20")

        val simpleSQLiteQuery = SimpleSQLiteQuery(queryString.toString())
        return movieDao.getMovieEntityByQuery(simpleSQLiteQuery).map { it.toGeneralMovie() }
    }

    override suspend fun getSearchMovieCount(query: String, pageNumber: Int): Int {
        val queryArgs = query.split("+")

        val queryString =
            constructSearchQuery(
                queryArgs,
                MovieDbContract.TABLE_NAME,
                MovieDbContract.ORIGINAL_TITLE
            ).insert(0, "SELECT COUNT(movie_id) FROM(\n").append("\n)")

        val simpleSQLiteQuery = SimpleSQLiteQuery(queryString.toString())
        return movieDao.getSearchMovieCount(simpleSQLiteQuery)
    }
}
