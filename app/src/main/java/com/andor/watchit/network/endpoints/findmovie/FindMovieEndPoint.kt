package com.andor.watchit.network.endpoints.findmovie

import com.andor.watchit.network.schema.TopRatedMovieSchema

interface FindMovieEndPoint {
    interface Listener {
        fun onFetchFailed()
        fun onFetchSuccess(movieSchema: TopRatedMovieSchema)
    }

    fun findMovie(page: Int, query: String, listener: Listener)
}