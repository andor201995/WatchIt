package com.andor.watchit.screens.moviedetail.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.watchit.R
import com.andor.watchit.screens.common.mvc.BaseViewMvc

class MovieDetailViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater
) : MovieDetailViewMvc, BaseViewMvc() {
    init {
        setRootView(inflater.inflate(R.layout.movie_detail_fragment, parent, false))
    }
}