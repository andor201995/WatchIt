package com.andor.watchit.network.common

import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun fetchTopRatedMovie(
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): Call<TopRatedMovieSchema>

    @GET("movie")
    fun findMovie(
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1",
        @Query("query") query: String
    ): Call<TopRatedMovieSchema>
}
