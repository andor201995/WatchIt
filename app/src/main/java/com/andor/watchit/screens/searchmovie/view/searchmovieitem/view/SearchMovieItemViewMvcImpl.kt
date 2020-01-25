package com.andor.watchit.screens.searchmovie.view.searchmovieitem.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.andor.watchit.R
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.searchmovie.model.Event
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.jakewharton.rxbinding3.view.clicks

class SearchMovieItemViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : SearchMovieItemViewMvc, BaseObservableViewMvc<Event>() {
    private var moviePosterContainer: View
    private var moviePosterImageView: ImageView

    init {
        //using  same item for search view
        setRootView(inflater.inflate(R.layout.top_rated_movie_list_item, parent, false))
        moviePosterImageView = findViewById(R.id.moviePosterImageView)
        moviePosterContainer = findViewById(R.id.moviePosterContainer)
    }

    override fun bindItem(item: GeneralMovie) {
        moviePosterContainer.clicks().map {
            Event.OpenMovie(item)
        }.subscribe(getEventStream())

        imageLoader.loadImageInto(moviePosterImageView, item.posterPath)
    }
}