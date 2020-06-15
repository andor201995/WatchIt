package com.andor.watchit.repository.movie

import com.andor.watchit.usecase.common.model.GeneralMovie

interface MovieDataSource {
    suspend fun add(generalMovie: GeneralMovie)
    suspend fun addAll(generalMovieList: List<GeneralMovie>)
    suspend fun get(movieId: Double): GeneralMovie?
    suspend fun getAll(): List<GeneralMovie>
    suspend fun remove(generalMovie: GeneralMovie)
    suspend fun getpage(pageNumber: Int, pageSize: Int = 20): List<GeneralMovie>
    suspend fun getCachedMovieCount(): Int
    suspend fun getMoviesByName(query: String, pageNumber: Int): List<GeneralMovie>
    suspend fun getSearchMovieCount(query: String, pageNumber: Int): Int
}
