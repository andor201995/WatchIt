package com.andor.watchit.screens.posterview.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.andor.watchit.R
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.posterview.model.Event
import com.andor.watchit.usecase.common.model.GeneralMovie

class PosterViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : PosterViewMvc, BaseObservableViewMvc<Event>() {
    private val posterMovieTitleTextView: TextView
    private val posterMovieImageView: ImageView

    init {
        setRootView(inflater.inflate(R.layout.poster_fragment, parent, false))
        posterMovieImageView = findViewById(R.id.posterMovieImageView)
        posterMovieTitleTextView = findViewById(R.id.posterMovieTitleTextView)
    }

    override fun setMoviePoster(movieDetail: GeneralMovie) {

        posterMovieImageView.also {
            imageLoader.loadImageInto(it, movieDetail.posterPath)
        }

        posterMovieTitleTextView.also {
            it.text = movieDetail.originalTitle
        }
    }
}