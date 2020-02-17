package com.andor.watchit.usecase.populartv

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.populartv.PopularTvEndPoint
import com.andor.watchit.usecase.common.model.GeneralTv
import io.reactivex.Single
import io.reactivex.SingleSource

class PopularTvUseCaseImpl(private val popularTvEndPoint: PopularTvEndPoint) :
    PopularTvUseCase {

    override fun getPopularTv(pageNumber: Int): Single<List<GeneralTv>> {
        return popularTvEndPoint.getPopularMovies(pageNumber).flatMap { schema ->
            val generalTv: List<GeneralTv> = Converter.convertFrom(schema)
            SingleSource<List<GeneralTv>> {
                it.onSuccess(generalTv)
            }
        }
    }
}