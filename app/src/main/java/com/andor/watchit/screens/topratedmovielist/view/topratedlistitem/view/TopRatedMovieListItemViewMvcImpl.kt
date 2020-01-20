package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.andor.watchit.R
import com.andor.watchit.core.Constants
import com.andor.watchit.screens.common.mvc.BaseViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie
import com.squareup.picasso.Picasso

class TopRatedMovieListItemViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val picasso: Picasso
) :
    TopRatedMovieListItemViewMvc,
    BaseViewMvc() {
    private var moviePosterImageView: ImageView

    init {
        setRootView(inflater.inflate(R.layout.top_rated_movie_list_item, parent, false))
        moviePosterImageView = findViewById<ImageView>(R.id.moviePosterImageView)


    }

    override fun updateView(topRatedMovie: TopRatedMovie) {
        picasso
            .load("${Constants.BASE_IMAGE_URL}/${Constants.IMAGE_SIZE}/${topRatedMovie.posterPath}")
            .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_image_24px)!!)
            .error(ContextCompat.getDrawable(context, R.drawable.ic_error_24px)!!)
            .into(moviePosterImageView)

    }
}