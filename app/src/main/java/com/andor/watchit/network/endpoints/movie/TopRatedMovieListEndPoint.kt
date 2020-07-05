package com.andor.watchit.network.endpoints.movie

import com.andor.watchit.network.api.MovieApi
import com.andor.watchit.network.common.schema.MovieSchema
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Contract between use-case and
 * top rated movie endpoint impl
 */
interface TopRatedMovieListEndPoint {
    interface Listener {
        fun onFetchSuccess(movieSchema: MovieSchema)
        fun onFetchFailed()
    }

    fun onFetchTopRatedMovieListAndNotify(
        pageNumber: Int,
        listener: Listener
    )
}

class TopRatedMovieListEndPointImpl @Inject constructor(private val movieApi: MovieApi) :
    TopRatedMovieListEndPoint {

    override fun onFetchTopRatedMovieListAndNotify(
        pageNumber: Int,
        listener: TopRatedMovieListEndPoint.Listener
    ) {
        movieApi.fetchTopRatedMovie(page = pageNumber.toString())
            .enqueue(object : Callback<MovieSchema> {
                override fun onFailure(call: Call<MovieSchema>, t: Throwable) {
                    listener.onFetchFailed()
                }

                override fun onResponse(
                    call: Call<MovieSchema>,
                    response: Response<MovieSchema>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        listener.onFetchSuccess(response.body()!!)
                    } else {
                        listener.onFetchFailed()
                    }
                }
            }
            )
    }
}
