package com.andor.watchit.network.endpoints.search

import com.andor.watchit.network.api.MovieApi
import com.andor.watchit.network.common.schema.MovieSchema
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface FindMovieEndPoint {
    interface Listener {
        fun onFetchFailed()
        fun onFetchSuccess(movieSchema: MovieSchema)
    }

    fun findMovieAndNotify(page: Int, query: String, listener: Listener)
}

class FindMovieEndPointImpl @Inject constructor(private val movieApi: MovieApi) :
    FindMovieEndPoint {

    override fun findMovieAndNotify(
        page: Int,
        query: String,
        listener: FindMovieEndPoint.Listener
    ) {
        movieApi.findMovie(page = page.toString(), query = query)
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
            })
    }
}
