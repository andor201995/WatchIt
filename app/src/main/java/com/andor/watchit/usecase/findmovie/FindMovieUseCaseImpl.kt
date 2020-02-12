package com.andor.watchit.usecase.findmovie

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.network.findmovie.FindMovieEndPoint
import com.andor.watchit.usecase.findmovie.FindMovieUseCase.FetchResult
import io.reactivex.subjects.SingleSubject

class FindMovieUseCaseImpl(private val findMovieEndPoint: FindMovieEndPoint) : FindMovieUseCase {

    var oldQuery = ""
    lateinit var oldResult: FetchResult

    override fun findMovie(page: Int, query: String): SingleSubject<FetchResult> {
        val responseSingle = SingleSubject.create<FetchResult>()
        val convertedQuery = Converter.convertFrom(query)

        if (convertedQuery.isNotBlank() && convertedQuery != oldQuery) {
            findMovieEndPoint.findMovieAndNotify(
                page,
                convertedQuery,
                object : FindMovieEndPoint.Listener {
                    override fun onFetchFailed() {
                        responseSingle.onSuccess(FetchResult.Failure)
                    }

                    override fun onFetchSuccess(movieSchema: TopRatedMovieSchema) {
                        val responseResult = FetchResult.Success(
                            Converter.convertFrom(movieSchema),
                            movieSchema.page,
                            movieSchema.total_pages,
                            movieSchema.total_results
                        )
                        oldQuery = convertedQuery
                        oldResult = responseResult

                        responseSingle.onSuccess(
                            responseResult
                        )
                    }

                })
        } else if (::oldResult.isInitialized && convertedQuery == oldQuery) {
            responseSingle.onSuccess(oldResult)
        } else {
            responseSingle.onSuccess(FetchResult.InvalidQuery)
        }

        return responseSingle
    }
}