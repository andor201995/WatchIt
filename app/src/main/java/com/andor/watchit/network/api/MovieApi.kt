package com.andor.watchit.network.api

import com.andor.watchit.network.common.schema.MovieSchema
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated")
    fun fetchTopRatedMovie(
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): Call<MovieSchema>

    @GET("search/movie")
    fun findMovie(
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1",
        @Query("query") query: String
    ): Call<MovieSchema>
}
