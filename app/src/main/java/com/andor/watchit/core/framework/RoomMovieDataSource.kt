package com.andor.watchit.core.framework

import com.andor.watchit.core.framework.db.MovieDao
import com.andor.watchit.core.framework.db.MovieEntity
import com.andor.watchit.repository.MovieDataSource
import com.andor.watchit.usecase.common.model.GeneralMovie

class RoomMovieDataSource(private val movieDao: MovieDao) : MovieDataSource {

    override suspend fun add(generalMovie: GeneralMovie) =
        movieDao.addMovieEntity(MovieEntity.fromGeneralMovie(generalMovie))

    override suspend fun get(movieId: Double) = movieDao.getMovieEntity(movieId)?.toGeneralMovie()

    override suspend fun getAll(): List<GeneralMovie> =
        movieDao.getAllMovieEntity().map { it.toGeneralMovie() }

    override suspend fun remove(generalMovie: GeneralMovie) =
        movieDao.deleteMovie(MovieEntity.fromGeneralMovie(generalMovie))
}