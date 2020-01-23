package com.andor.watchit.usecase.topratedmovie

import com.andor.watchit.network.endpoints.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.network.helper.Converter
import com.andor.watchit.network.schema.TopRatedMovieSchema
import io.reactivex.subjects.SingleSubject

class TopRatedMovieUseCaseImpl(private val topRatedMovieListEndPoint: TopRatedMovieListEndPoint) :
    TopRatedMovieUseCase {

    sealed class FetchResult {
        data class Success(
            val topRatedMovieList: List<TopRatedMovie>,
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