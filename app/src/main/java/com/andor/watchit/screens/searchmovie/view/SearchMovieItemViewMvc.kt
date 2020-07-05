package com.andor.watchit.screens.searchmovie.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.andor.watchit.R
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.searchmovie.model.SearchViewEvent
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.jakewharton.rxbinding3.view.clicks

interface SearchMovieItemViewMvc : ObservableViewMvc<SearchViewEvent> {
    fun bindItem(item: MovieUiModel)
    fun cleanUp()
}

class SearchMovieItemViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : SearchMovieItemViewMvc, BaseObservableViewMvc<SearchViewEvent>() {
    private var moviePosterContainer: View
    private var moviePosterImageView: ImageView

    init {
        // using  same item for search view
        setRootView(inflater.inflate(R.layout.list_item, parent, false))
        moviePosterImageView = findViewById(R.id.posterImageView)
        moviePosterContainer = findViewById(R.id.posterContainer)
    }

    override fun bindItem(item: MovieUiModel) {
        moviePosterContainer.clicks().map {
            SearchViewEvent.OpenMovie(item)
        }.subscribe(getEventStream())

        imageLoader.loadImageInto(moviePosterImageView, item.posterPath)
    }

    override fun cleanUp() {
        imageLoader.cleanUp(moviePosterImageView)
    }
}
