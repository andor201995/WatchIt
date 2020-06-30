package com.andor.watchit.network.endpoints.tv

import com.andor.watchit.network.api.TvApi
import com.andor.watchit.network.common.schema.TvSchema
import io.reactivex.Single
import javax.inject.Inject

interface PopularTvEndPoint {
    fun getPopularMovies(pageNumber: Int): Single<TvSchema>
}

class PopularTvEndPointImpl @Inject constructor(private val tvApi: TvApi) : PopularTvEndPoint {
    override fun getPopularMovies(pageNumber: Int): Single<TvSchema> {
        return tvApi.getPopularSeries(page = pageNumber.toString())
    }
}
