package com.andor.watchit.network

import com.andor.watchit.network.schema.TopRatedMovieSchema
import retrofit2.Call

interface MovieApi {
    fun fetchTopRatedMovie(): Call<TopRatedMovieSchema>
}
