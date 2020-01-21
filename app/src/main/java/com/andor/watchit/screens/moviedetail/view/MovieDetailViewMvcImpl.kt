package com.andor.watchit.screens.moviedetail.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.andor.watchit.R
import com.andor.watchit.core.Constants
import com.andor.watchit.screens.common.mvc.BaseViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie
import com.squareup.picasso.Picasso

class MovieDetailViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val picasso: Picasso
) : MovieDetailViewMvc, BaseViewMvc() {
    private var movieDetailPosterImageView: ImageView

    init {
        setRootView(inflater.inflate(R.layout.movie_detail_fragment, parent, false))
        movieDetailPosterImageView = findViewById(R.id.movieDetailPosterImageView)
    }

    override fun setMovieDetails(movieDetail: TopRatedMovie) {
        movieDetailPosterImageView.also {

            ViewCompat.setTransitionName(it, movieDetail.posterPath)
            picasso
                .load("${Constants.BASE_IMAGE_URL}/${Constants.IMAGE_SIZE}/${movieDetail.posterPath}")
                .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_image_24px)!!)
                .error(ContextCompat.getDrawable(context, R.drawable.ic_error_24px)!!)
                .into(it)
        }

    }
}