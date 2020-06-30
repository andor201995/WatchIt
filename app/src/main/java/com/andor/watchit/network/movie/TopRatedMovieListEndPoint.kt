package com.andor.watchit.network.movie

import com.andor.watchit.network.common.schema.TopRatedMovieSchema

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
