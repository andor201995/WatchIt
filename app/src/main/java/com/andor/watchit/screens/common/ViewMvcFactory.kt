package com.andor.watchit.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.watchit.screens.networkerror.view.NetworkErrorViewMvc
import com.andor.watchit.screens.networkerror.view.NetworkErrorViewMvcImpl
import com.andor.watchit.screens.topratedmovielist.view.TopRatedMovieListViewMvc
import com.andor.watchit.screens.topratedmovielist.view.TopRatedMovieListViewMvcImpl
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemViewMvc
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemViewMvcImpl
import com.squareup.picasso.Picasso

class ViewMvcFactory(private val layoutInflater: LayoutInflater, private val picasso: Picasso) {
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
            picasso
        )
    }

    fun getNetworkErrorViewMvc(parent: ViewGroup?): NetworkErrorViewMvc {
        return NetworkErrorViewMvcImpl(parent, layoutInflater)
    }

}
