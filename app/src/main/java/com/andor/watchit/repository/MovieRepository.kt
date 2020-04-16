package com.andor.watchit.repository

import com.andor.watchit.usecase.common.model.GeneralMovie

class MovieRepository(private val dataSource: MovieDataSource) {
    suspend fun addMovie(generalMovie: GeneralMovie) = dataSource.add(generalMovie)
    suspend fun getMovie(movieId: Double) = dataSource.get(movieId)
    suspend fun getAllMovies() = dataSource.getAll()
    suspend fun removeMovie(generalMovie: GeneralMovie) = dataSource.remove(generalMovie)
}