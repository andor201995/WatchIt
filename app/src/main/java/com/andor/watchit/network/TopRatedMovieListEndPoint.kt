package com.andor.watchit.network

import com.andor.watchit.network.schema.TopRatedMovieSchema

interface TopRatedMovieListEndPoint {
    interface Listener {
        fun onFetchSuccess(topRatedMovieSchema: TopRatedMovieSchema)
    }
    fun onFetchTopRatedMovieListAndNotify(listener: Listener)
}