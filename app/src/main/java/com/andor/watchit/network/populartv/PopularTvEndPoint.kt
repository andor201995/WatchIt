package com.andor.watchit.network.populartv

import com.andor.watchit.network.common.schema.GeneralTvSchema
import io.reactivex.Single

interface PopularTvEndPoint {
    fun getPopularMovies(pageNumber: Int): Single<GeneralTvSchema>
}