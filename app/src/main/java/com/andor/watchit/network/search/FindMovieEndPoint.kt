package com.andor.watchit.network.search

import com.andor.watchit.network.common.schema.TopRatedMovieSchema

interface FindMovieEndPoint {
    interface Listener {
        fun onFetchFailed()
        fun onFetchSuccess(movieSchema: TopRatedMovieSchema)
    }

    fun findMovieAndNotify(page: Int, query: String, listener: Listener)
}
