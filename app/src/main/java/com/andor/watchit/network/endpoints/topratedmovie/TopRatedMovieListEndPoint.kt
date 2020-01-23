package com.andor.watchit.network.endpoints.topratedmovie

import com.andor.watchit.network.schema.TopRatedMovieSchema

interface TopRatedMovieListEndPoint {
    interface Listener {
        fun onFetchSuccess(topRatedMovieSchema: TopRatedMovieSchema)
        fun onFetchFailed()
    }
    fun onFetchTopRatedMovieListAndNotify(
        pageNumber: Int,
        listener: Listener
    )
}