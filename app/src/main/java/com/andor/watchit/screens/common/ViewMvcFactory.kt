package com.andor.watchit.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.moviedetail.view.MovieDetailViewMvc
import com.andor.watchit.screens.moviedetail.view.MovieDetailViewMvcImpl
import com.andor.watchit.screens.networkerror.view.NetworkErrorViewMvc
import com.andor.watchit.screens.networkerror.view.NetworkErrorViewMvcImpl
import com.andor.watchit.screens.posterview.view.PosterViewMvc
import com.andor.watchit.screens.posterview.view.PosterViewMvcImpl
import com.andor.watchit.screens.topratedmovielist.view.TopRatedMovieListViewMvc
import com.andor.watchit.screens.topratedmovielist.view.TopRatedMovieListViewMvcImpl
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemLoaderViewMvc
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemLoaderViewMvcImpl
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemViewMvc
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemViewMvcImpl

class ViewMvcFactory(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader
) {
    fun getTopRatedMovieMvc(parent: ViewGroup?): TopRatedMovieListViewMvc {
        return TopRatedMovieListViewMvcImpl(
            parent,
            layoutInflater,
            this
        )
    }

    fun getTopRatedMovieListItemViewMvc(parent: ViewGroup?): TopRatedMovieListItemViewMvc {
        return TopRatedMovieListItemViewMvcImpl(
            parent,
            layoutInflater,
            imageLoader
        )
    }

    fun getNetworkErrorViewMvc(parent: ViewGroup?): NetworkErrorViewMvc {
        return NetworkErrorViewMvcImpl(
            parent,
            layoutInflater
        )
    }

    fun getTopRatedMovieListItemLoaderViewMvc(parent: ViewGroup?): TopRatedMovieListItemLoaderViewMvc {
        return TopRatedMovieListItemLoaderViewMvcImpl(
            parent,
            layoutInflater
        )
    }

    fun getMovieDetailViewMvc(parent: ViewGroup?): MovieDetailViewMvc {
        return MovieDetailViewMvcImpl(
            parent,
            layoutInflater,
            imageLoader
        )
    }

    fun getPosterViewMvc(parent: ViewGroup?): PosterViewMvc {
        return PosterViewMvcImpl(
            parent,
            layoutInflater,
            imageLoader
        )
    }

}
