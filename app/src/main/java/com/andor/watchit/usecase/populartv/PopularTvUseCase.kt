package com.andor.watchit.usecase.populartv

import com.andor.watchit.usecase.common.model.GeneralTv
import io.reactivex.Single

interface PopularTvUseCase {
    fun getPopularTv(pageNumber: Int): Single<List<GeneralTv>>
}