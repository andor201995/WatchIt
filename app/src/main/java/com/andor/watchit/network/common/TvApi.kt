package com.andor.watchit.network.common

import com.andor.watchit.network.common.schema.GeneralTvSchema
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TvApi {

    @GET("tv/popular")
    fun getPopularSeries(
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): Single<GeneralTvSchema>

}