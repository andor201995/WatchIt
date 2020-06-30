package com.andor.watchit.screens.movielist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.andor.watchit.R
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.movielist.model.MovieListEvent
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.jakewharton.rxbinding3.view.clicks

interface TopRatedMovieListItemViewMvc : ObservableViewMvc<MovieListEvent> {
    fun updateView(movieUiModel: MovieUiModel)
    fun cleanUp()
}

class TopRatedMovieListItemViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val imageLoader: ImageLoader
) :
    TopRatedMovieListItemViewMvc,
    BaseObservableViewMvc<MovieListEvent>() {
    private var moviePosterContainer: View
    private var moviePosterImageView: ImageView

    init {
        setRootView(inflater.inflate(R.layout.list_item, parent, false))
        moviePosterImageView = findViewById(R.id.posterImageView)
        moviePosterContainer = findViewById(R.id.posterContainer)
    }

    override fun updateView(movieUiModel: MovieUiModel) {
        moviePosterContainer.clicks().map {
            MovieListEvent.LoadMovie(movieUiModel)
        }.subscribe(getEventStream())

        moviePosterImageView.also {
            imageLoader.loadImageInto(it, movieUiModel.posterPath)
        }
    }

    override fun cleanUp() {
        imageLoader.cleanUp(moviePosterImageView)
    }
}
