package com.andor.watchit.network.topratedmovie

import com.andor.watchit.network.common.MovieApi
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedMovieListEndPointImpl @Inject constructor(private val movieApi: MovieApi) :
    TopRatedMovieListEndPoint {

    override fun onFetchTopRatedMovieListAndNotify(
        pageNumber: Int,
        listener: TopRatedMovieListEndPoint.Listener
    ) {
        movieApi.fetchTopRatedMovie(page = pageNumber.toString())
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
            }
            )
    }
}
