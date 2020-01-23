package com.andor.watchit.network.endpoints.findmovie

import com.andor.watchit.network.api.MovieApi
import com.andor.watchit.network.schema.TopRatedMovieSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindMovieEndPointImpl(private val movieApi: MovieApi) : FindMovieEndPoint {

    override fun findMovie(page: Int, query: String, listener: FindMovieEndPoint.Listener) {
        movieApi.findMovie(page = page.toString(), query = query)
            .enqueue(object : Callback<TopRatedMovieSchema> {
                override fun onFailure(call: Call<TopRatedMovieSchema>, t: Throwable) {
                    listener.onFetchFailed()
                }

                override fun onResponse(
                    call: Call<TopRatedMovieSchema>,
                    response: Response<TopRatedMovieSchema>
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