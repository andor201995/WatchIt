package com.andor.watchit.usecase.topratedmovie

import com.andor.watchit.core.Convertor
import com.andor.watchit.network.endpoints.TopRatedMovieListEndPoint
import com.andor.watchit.network.schema.TopRatedMovieSchema
import io.reactivex.subjects.PublishSubject

class TopRatedMovieUseCase(private val topRatedMovieListEndPoint: TopRatedMovieListEndPoint) :
    TopRatedMovieListEndPoint.Listener {

    sealed class FetchResult {
        data class Success(val topRatedMovieList: List<TopRatedMovie>) : FetchResult()
        object Failure : FetchResult()
    }

    private val topRatedMovieFetchEventStream: PublishSubject<FetchResult> = PublishSubject.create()

    fun fetchTopRatedMovieAndNotify() {
        topRatedMovieListEndPoint.onFetchTopRatedMovieListAndNotify(this)
    }

    override fun onFetchSuccess(topRatedMovieSchema: TopRatedMovieSchema) {
        topRatedMovieFetchEventStream.onNext(
            FetchResult.Success(
                Convertor.convertFrom(
                    topRatedMovieSchema
                )
            )
        )
    }

    override fun onFetchFailed() {
        topRatedMovieFetchEventStream.onNext(
            FetchResult.Failure
        )
    }

    fun getResultStream(): PublishSubject<FetchResult> {
        return topRatedMovieFetchEventStream
    }
}