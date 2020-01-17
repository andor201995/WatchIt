package com.andor.watchit.network.endpoints

import com.andor.watchit.network.api.MovieApi
import com.andor.watchit.network.endpoints.TopRatedMovieListEndPoint
import com.andor.watchit.network.schema.TopRatedMovieSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedMovieListEndPointImpl(private val movieApi: MovieApi) :
    TopRatedMovieListEndPoint {

    override fun onFetchTopRatedMovieListAndNotify(listener: TopRatedMovieListEndPoint.Listener) {
        movieApi.fetchTopRatedMovie().enqueue(object : Callback<TopRatedMovieSchema> {
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