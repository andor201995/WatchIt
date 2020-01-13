package com.andor.watchit.network

import com.andor.watchit.network.schema.TopRatedMovieSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedMovieListEndPointImpl(private val movieApi: MovieApi) : TopRatedMovieListEndPoint {

    override fun onFetchTopRatedMovieListAndNotify(listener: TopRatedMovieListEndPoint.Listener) {
        movieApi.fetchTopRatedMovie().enqueue(object : Callback<TopRatedMovieSchema> {
            override fun onFailure(call: Call<TopRatedMovieSchema>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<TopRatedMovieSchema>,
                response: Response<TopRatedMovieSchema>
            ) {
                if (response.isSuccessful) {
                    listener.onFetchSuccess(TopRatedMovieSchema())
                }
            }
        }
        )
    }
}