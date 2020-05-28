package com.andor.watchit.network.findmovie

import com.andor.watchit.network.common.MovieApi
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindMovieEndPointImpl @Inject constructor(private val movieApi: MovieApi) :
    FindMovieEndPoint {

    override fun findMovieAndNotify(
        page: Int,
        query: String,
        listener: FindMovieEndPoint.Listener
    ) {
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
