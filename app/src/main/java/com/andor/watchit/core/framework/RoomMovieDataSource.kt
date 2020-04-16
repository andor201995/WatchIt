package com.andor.watchit.core.framework

import android.content.Context
import com.andor.watchit.core.framework.db.DatabaseService
import com.andor.watchit.core.framework.db.MovieEntity
import com.andor.watchit.repository.MovieDataSource
import com.andor.watchit.usecase.common.model.GeneralMovie

class RoomMovieDataSource(context: Context) : MovieDataSource {

    val movieDao = DatabaseService.getInstance(context).getMovieDao()

    override suspend fun add(generalMovie: GeneralMovie) =
        movieDao.addMovieEntity(MovieEntity.fromGeneralMovie(generalMovie))

    override suspend fun get(movieId: Double) = movieDao.getMovieEntity(movieId)?.toGeneralMovie()

    override suspend fun getAll(): List<GeneralMovie> =
        movieDao.getAllMovieEntity().map { it.toGeneralMovie() }

    override suspend fun remove(generalMovie: GeneralMovie) =
        movieDao.deleteMovie(MovieEntity.fromGeneralMovie(generalMovie))
}