package com.andor.watchit.screens.posterview.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.andor.watchit.R
import com.andor.watchit.core.Constants
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.posterview.model.Event
import com.andor.watchit.usecase.common.datasource.GeneralMovie
import com.squareup.picasso.Picasso

class PosterViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val picasso: Picasso
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
            picasso
                .load("${Constants.BASE_IMAGE_URL}/${Constants.IMAGE_SIZE}/${movieDetail.posterPath}")
                .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_image_24px)!!)
                .error(ContextCompat.getDrawable(context, R.drawable.ic_error_24px)!!)
                .into(it)
        }

        posterMovieTitleTextView.also {
            it.text = movieDetail.originalTitle
        }
    }
}