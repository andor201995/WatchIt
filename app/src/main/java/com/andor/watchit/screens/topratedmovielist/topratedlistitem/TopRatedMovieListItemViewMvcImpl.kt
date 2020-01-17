package com.andor.watchit.screens.topratedmovielist.topratedlistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.andor.watchit.R
import com.andor.watchit.screens.common.mvc.BaseViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

class TopRatedMovieListItemViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater
) :
    TopRatedMovieListItemViewMvc,
    BaseViewMvc() {
    init {
        setRootView(inflater.inflate(R.layout.top_rated_movie_list_item, parent, false))
    }

    override fun updateView(topRatedMovie: TopRatedMovie) {
        val originalNameTextView = findViewById<TextView>(R.id.originalName)
        originalNameTextView.text = topRatedMovie.originalTitle
    }
}