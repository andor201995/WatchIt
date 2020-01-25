package com.andor.watchit.usecase.findmovie

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.network.findmovie.FindMovieEndPoint
import com.andor.watchit.usecase.findmovie.FindMovieUseCase.FetchResult
import io.reactivex.subjects.SingleSubject

class FindMovieUseCaseImpl(private val findMovieEndPoint: FindMovieEndPoint) : FindMovieUseCase {

    override fun findMovie(page: Int, query: String): SingleSubject<FetchResult> {
        val responseSingle = SingleSubject.create<FetchResult>()
        val convertedQuery = Converter.convertFrom(query)
        findMovieEndPoint.findMovieAndNotify(
            page,
            convertedQuery,
            object : FindMovieEndPoint.Listener {
                override fun onFetchFailed() {
                    responseSingle.onSuccess(FetchResult.Failure)
                }

                override fun onFetchSuccess(movieSchema: TopRatedMovieSchema) {
                    responseSingle.onSuccess(
                        FetchResult.Success(
                            Converter.convertFrom(movieSchema),
                            movieSchema.page,
                            movieSchema.total_pages,
                            movieSchema.total_results
                        )
                    )
                }

            })
        return responseSingle
    }
}