package com.andor.watchit.repository.movie

import com.andor.watchit.usecase.common.model.GeneralMovie

class MovieRepository(private val dataSource: MovieDataSource) {
    suspend fun addMovie(generalMovie: GeneralMovie) = dataSource.add(generalMovie)

    suspend fun addAllMovie(generalMovieList: List<GeneralMovie>) =
        dataSource.addAll(generalMovieList)

    suspend fun getMovie(movieId: Double) = dataSource.get(movieId)

    suspend fun getAllMovies() = dataSource.getAll()

    suspend fun getPagedMovies(pageNumber: Int) = dataSource.getPage(pageNumber)

    suspend fun removeMovie(generalMovie: GeneralMovie) = dataSource.remove(generalMovie)

    suspend fun getMovieCount(): Int = dataSource.getCachedMovieCount()

    suspend fun getMoviesByName(query: String, pageNumber: Int): List<GeneralMovie> =
        dataSource.getMoviesByName(query, pageNumber)

    suspend fun getSearchCount(query: String, page: Int): Int =
        dataSource.getSearchMovieCount(query, page)
}
