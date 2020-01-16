package com.andor.watchit.screens.topratedmovielist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.watchit.R
import com.andor.watchit.screens.common.mvc.BaseViewMvc

class TopRatedMovieListViewMvcImpl(
    parent: ViewGroup?,
    layoutInflater: LayoutInflater
) : BaseViewMvc(), TopRatedMovieListViewMvc {

    init {
        layoutInflater.inflate(R.layout.top_rated_movie_list_fragment, parent, false)
    }
}