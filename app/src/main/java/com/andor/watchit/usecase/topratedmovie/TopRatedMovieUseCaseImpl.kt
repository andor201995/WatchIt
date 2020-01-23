package com.andor.watchit.usecase.topratedmovie

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.usecase.common.datasource.GeneralMovie
import io.reactivex.subjects.SingleSubject

class TopRatedMovieUseCaseImpl(private val topRatedMovieListEndPoint: TopRatedMovieListEndPoint) :
    TopRatedMovieUseCase {

    sealed class FetchResult {
        data class Success(
            val generalMovieList: List<GeneralMovie>,
            val pageNumber: Int,
            val maxPageCount: Int
        ) : FetchResult()

        object Failure : FetchResult()
    }

    override fun fetchTopRatedMovieAndNotify(pageNumber: Int): SingleSubject<FetchResult> {
        val topRatedMovieFetchEvent: SingleSubject<FetchResult> = SingleSubject.create()
        topRatedMovieListEndPoint.onFetchTopRatedMovieListAndNotify(pageNumber,
            object : TopRatedMovieListEndPoint.Listener {
                override fun onFetchSuccess(topRatedMovieSchema: TopRatedMovieSchema) {
                    topRatedMovieFetchEvent.onSuccess(
                        FetchResult.Success(
                            Converter.convertFrom(
                                topRatedMovieSchema
                            ),
                            topRatedMovieSchema.page,
                            topRatedMovieSchema.total_pages
                        )
                    )
                }

                override fun onFetchFailed() {
                    topRatedMovieFetchEvent.onSuccess(
                        FetchResult.Failure
                    )
                }
            })

        return topRatedMovieFetchEvent
    }
}