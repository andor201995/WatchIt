package com.andor.watchit.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.listdetail.view.MovieDetailViewMvc
import com.andor.watchit.screens.listdetail.view.MovieDetailViewMvcImpl
import com.andor.watchit.screens.movielist.view.TopRatedMovieListItemLoaderViewMvc
import com.andor.watchit.screens.movielist.view.TopRatedMovieListItemLoaderViewMvcImpl
import com.andor.watchit.screens.movielist.view.TopRatedMovieListItemViewMvc
import com.andor.watchit.screens.movielist.view.TopRatedMovieListItemViewMvcImpl
import com.andor.watchit.screens.movielist.view.TopRatedMovieListViewMvc
import com.andor.watchit.screens.movielist.view.TopRatedMovieListViewMvcImpl
import com.andor.watchit.screens.networkerror.view.NetworkErrorViewMvc
import com.andor.watchit.screens.networkerror.view.NetworkErrorViewMvcImpl
import com.andor.watchit.screens.posterview.view.PosterViewMvc
import com.andor.watchit.screens.posterview.view.PosterViewMvcImpl
import com.andor.watchit.screens.searchmovie.view.SearchMovieItemViewMvc
import com.andor.watchit.screens.searchmovie.view.SearchMovieItemViewMvcImpl
import com.andor.watchit.screens.searchmovie.view.SearchMovieViewMvc
import com.andor.watchit.screens.searchmovie.view.SearchMovieViewMvcImpl
import com.andor.watchit.screens.tvlist.view.TvListItemLoaderViewMvc
import com.andor.watchit.screens.tvlist.view.TvListItemLoaderViewMvcImpl
import com.andor.watchit.screens.tvlist.view.TvListItemViewMvc
import com.andor.watchit.screens.tvlist.view.TvListItemViewMvcImpl
import com.andor.watchit.screens.tvlist.view.TvListViewMvc
import com.andor.watchit.screens.tvlist.view.TvListViewMvcImpl

class ViewMvcFactory(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader
) {
    fun getTopRatedMovieMvc(parent: ViewGroup?): TopRatedMovieListViewMvc =
        TopRatedMovieListViewMvcImpl(
            parent,
            layoutInflater,
            this
        )

    fun getTopRatedMovieListItemViewMvc(parent: ViewGroup?): TopRatedMovieListItemViewMvc =
        TopRatedMovieListItemViewMvcImpl(
            parent,
            layoutInflater,
            imageLoader
        )

    fun getNetworkErrorViewMvc(parent: ViewGroup?): NetworkErrorViewMvc =
        NetworkErrorViewMvcImpl(
            parent,
            layoutInflater
        )

    fun getTopRatedMovieListItemLoaderViewMvc(parent: ViewGroup?):
            TopRatedMovieListItemLoaderViewMvc =
        TopRatedMovieListItemLoaderViewMvcImpl(
            parent,
            layoutInflater
        )

    fun getMovieDetailViewMvc(parent: ViewGroup?): MovieDetailViewMvc =
        MovieDetailViewMvcImpl(
            parent,
            layoutInflater,
            imageLoader
        )

    fun getPosterViewMvc(parent: ViewGroup?): PosterViewMvc =
        PosterViewMvcImpl(
            parent,
            layoutInflater,
            imageLoader
        )

    fun getSearchViewMvc(parent: ViewGroup?): SearchMovieViewMvc =
        SearchMovieViewMvcImpl(
            parent,
            layoutInflater,
            this
        )

    fun getSearchMovieItemViewMvc(parent: ViewGroup): SearchMovieItemViewMvc =
        SearchMovieItemViewMvcImpl(
            parent,
            layoutInflater,
            imageLoader
        )

    fun getTvListViewMvc(parent: ViewGroup?): TvListViewMvc = TvListViewMvcImpl(
        parent,
        layoutInflater,
        this
    )

    fun getTvListItemLoaderViewMvc(parent: ViewGroup): TvListItemLoaderViewMvc =
        TvListItemLoaderViewMvcImpl(
            parent,
            layoutInflater
        )

    fun getTvListItemViewMvc(parent: ViewGroup): TvListItemViewMvc =
        TvListItemViewMvcImpl(
            parent,
            layoutInflater,
            imageLoader
        )
}
