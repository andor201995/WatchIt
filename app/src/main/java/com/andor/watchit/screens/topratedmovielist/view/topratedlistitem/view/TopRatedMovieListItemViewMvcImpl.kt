package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.andor.watchit.R
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.MovieListEvent
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.jakewharton.rxbinding3.view.clicks

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
        setRootView(inflater.inflate(R.layout.movie_list_item, parent, false))
        moviePosterImageView = findViewById(R.id.moviePosterImageView)
        moviePosterContainer = findViewById(R.id.moviePosterContainer)
    }

    override fun updateView(generalMovie: GeneralMovie) {
        moviePosterContainer.clicks().map {
            MovieListEvent.LoadMovie(generalMovie)
        }.subscribe(getEventStream())

        moviePosterImageView.also {
            imageLoader.loadImageInto(it, generalMovie.posterPath)
        }
    }

    override fun cleanUp() {
        imageLoader.cleanUp(moviePosterImageView)
    }
}
