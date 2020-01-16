package com.andor.watchit.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.watchit.screens.topratedmovielist.TopRatedMovieListViewMvc
import com.andor.watchit.screens.topratedmovielist.TopRatedMovieListViewMvcImpl

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {
    fun getTopRatedMovieMvc(parent: ViewGroup?): TopRatedMovieListViewMvc {
        return TopRatedMovieListViewMvcImpl(parent,layoutInflater)
    }

}
