package com.andor.watchit.network.populartv

import com.andor.watchit.network.common.TvApi
import com.andor.watchit.network.common.schema.GeneralTvSchema
import io.reactivex.Single

class PopularTvEndPointImpl(private val tvApi: TvApi) : PopularTvEndPoint {
    override fun getPopularMovies(pageNumber: Int): Single<GeneralTvSchema> {
        return tvApi.getPopularSeries(page = pageNumber.toString())
    }
}