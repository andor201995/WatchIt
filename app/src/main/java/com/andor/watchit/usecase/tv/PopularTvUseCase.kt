package com.andor.watchit.usecase.tv

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.tv.PopularTvEndPoint
import com.andor.watchit.repository.tv.TvRepository
import com.andor.watchit.usecase.common.model.TvUiModel
import io.reactivex.Single
import io.reactivex.SingleSource

interface PopularTvUseCase {
    fun getPopularTv(pageNumber: Int): Single<List<TvUiModel>>
}

class PopularTvUseCaseImpl(
    private val popularTvEndPoint: PopularTvEndPoint,
    private val repository: TvRepository
) :
    PopularTvUseCase {

    override fun getPopularTv(pageNumber: Int): Single<List<TvUiModel>> {
        return popularTvEndPoint.getPopularMovies(pageNumber).flatMap { schema ->
            val tvUiModel: List<TvUiModel> = Converter.convertFrom(schema)
            SingleSource<List<TvUiModel>> {
                it.onSuccess(tvUiModel)
            }
        }
    }
}
