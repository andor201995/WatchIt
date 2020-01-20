package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ContentLoadingProgressBar
import com.andor.watchit.R
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc

class TopRatedMovieListItemLoaderViewMvcImpl(parent: ViewGroup?, inflater: LayoutInflater) :
    TopRatedMovieListItemLoaderViewMvc,
    BaseObservableViewMvc<TopRatedMovieListItemLoaderViewMvc.Event>() {

    private var retryContainer: View
    private var container: View
    private var errorImageView: ImageView
    private val progressBar: ContentLoadingProgressBar

    init {
        setRootView(inflater.inflate(R.layout.top_rated_movie_list_item_loader, parent, false))
        progressBar = findViewById(R.id.itemLoader)
        errorImageView = findViewById(R.id.itemError)
        retryContainer = findViewById(R.id.retryContainer)
        container = findViewById(R.id.loaderItemContainer)

        retryContainer.setOnClickListener {
            getEventStream()
                .onNext(TopRatedMovieListItemLoaderViewMvc.Event.RetryListLoading)
        }

    }

    override fun showLoader() {
        progressBar.show()
        retryContainer.visibility = View.GONE
    }

    override fun showError() {
        retryContainer.visibility = View.VISIBLE
        progressBar.hide()
    }

    override fun hideRow() {
        container.visibility = View.GONE
    }
}