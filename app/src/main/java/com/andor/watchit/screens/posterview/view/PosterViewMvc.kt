package com.andor.watchit.screens.posterview.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.andor.watchit.R
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.listdetail.model.DetailUiModel
import com.andor.watchit.screens.posterview.model.PosterViewEvent

interface PosterViewMvc : ObservableViewMvc<PosterViewEvent> {
    fun setMoviePoster(detailUiModel: DetailUiModel)
}

class PosterViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : PosterViewMvc, BaseObservableViewMvc<PosterViewEvent>() {
    private val posterMovieTitleTextView: TextView
    private val posterMovieImageView: ImageView

    init {
        setRootView(inflater.inflate(R.layout.poster_fragment, parent, false))
        posterMovieImageView = findViewById(R.id.posterMovieImageView)
        posterMovieTitleTextView = findViewById(R.id.posterMovieTitleTextView)
    }

    override fun setMoviePoster(detailUiModel: DetailUiModel) {

        posterMovieImageView.also {
            imageLoader.loadImageInto(it, detailUiModel.posterPath)
        }

        posterMovieTitleTextView.also {
            it.text = detailUiModel.posterTitle
        }
    }
}
