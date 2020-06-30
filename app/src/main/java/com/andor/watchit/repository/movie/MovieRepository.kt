package com.andor.watchit.repository.movie

import com.andor.watchit.usecase.common.model.MovieUiModel

class MovieRepository(private val dataSource: MovieDataSource) {
    suspend fun addMovie(movieUiModel: MovieUiModel) = dataSource.add(movieUiModel)

    suspend fun addAllMovie(movieUiModelList: List<MovieUiModel>) =
        dataSource.addAll(movieUiModelList)

    suspend fun getMovie(movieId: Double) = dataSource.get(movieId)

    suspend fun getAllMovies() = dataSource.getAll()

    suspend fun getPagedMovies(pageNumber: Int) = dataSource.getPage(pageNumber)

    suspend fun removeMovie(movieUiModel: MovieUiModel) = dataSource.remove(movieUiModel)

    suspend fun getMovieCount(): Int = dataSource.getCachedMovieCount()

    suspend fun getMoviesByName(query: String, pageNumber: Int): List<MovieUiModel> =
        dataSource.getMoviesByName(query, pageNumber)

    suspend fun getSearchCount(query: String, page: Int): Int =
        dataSource.getSearchMovieCount(query, page)
}
