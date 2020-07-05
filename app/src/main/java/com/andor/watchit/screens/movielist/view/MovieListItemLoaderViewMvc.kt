package com.andor.watchit.screens.movielist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ContentLoadingProgressBar
import com.andor.watchit.R
import com.andor.watchit.core.extensions.gone
import com.andor.watchit.core.extensions.visible
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.movielist.model.MovieListEvent

interface TopRatedMovieListItemLoaderViewMvc :
    ObservableViewMvc<MovieListEvent> {
    fun showLoader()
    fun showError()
    fun hideRow()
}

class TopRatedMovieListItemLoaderViewMvcImpl(parent: ViewGroup?, inflater: LayoutInflater) :
    TopRatedMovieListItemLoaderViewMvc,
    BaseObservableViewMvc<MovieListEvent>() {

    private var retryContainer: View
    private var container: View
    private var errorImageView: ImageView
    private val progressBar: ContentLoadingProgressBar

    init {
        setRootView(inflater.inflate(R.layout.list_item_loader, parent, false))
        progressBar = findViewById(R.id.itemLoader)
        errorImageView = findViewById(R.id.itemError)
        retryContainer = findViewById(R.id.retryContainer)
        container = findViewById(R.id.loaderItemContainer)

        retryContainer.setOnClickListener {
            getEventStream()
                .onNext(MovieListEvent.RetryListLoading)
        }
    }

    override fun showLoader() {
        progressBar.show()
        progressBar.visible()
        retryContainer.gone()
    }

    override fun showError() {
        retryContainer.visible()
        progressBar.hide()
    }

    override fun hideRow() {
        container.gone()
    }
}
