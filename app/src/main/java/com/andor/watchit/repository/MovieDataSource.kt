package com.andor.watchit.repository

import com.andor.watchit.usecase.common.model.GeneralMovie

interface MovieDataSource {
    suspend fun add(generalMovie: GeneralMovie)
    suspend fun get(movieId: Double): GeneralMovie?
    suspend fun getAll(): List<GeneralMovie>
    suspend fun remove(generalMovie: GeneralMovie)
}