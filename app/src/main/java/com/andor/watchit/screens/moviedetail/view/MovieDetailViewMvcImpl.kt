package com.andor.watchit.screens.moviedetail.view

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.andor.watchit.R
import com.andor.watchit.core.appendTextWithColor
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.moviedetail.model.Event
import com.andor.watchit.usecase.common.datasource.GeneralMovie


class MovieDetailViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : MovieDetailViewMvc, BaseObservableViewMvc<Event>() {
    private var overViewTextView: TextView
    private var ratingTextView: TextView
    private var releaseDateTextView: TextView
    private var movieTitleTextView: TextView
    private var posterImageView: ImageView

    init {
        setRootView(inflater.inflate(R.layout.movie_detail_fragment, parent, false))
        posterImageView = findViewById(R.id.movieDetailPosterImageView)
        movieTitleTextView = findViewById(R.id.movieDetailMovieTitleTextView)
        overViewTextView = findViewById(R.id.movieDetailMovieOverviewTextView)
        ratingTextView = findViewById(R.id.movieDetailMovieRatingTextView)
        releaseDateTextView = findViewById(R.id.movieDetailMovieReleaseDataTextView)
    }

    @SuppressLint("SetTextI18n")
    override fun setMovieDetails(movieDetail: GeneralMovie) {
        posterImageView.also {
            imageLoader.loadImageInto(it, movieDetail.posterPath)
            it.setOnClickListener {
                getEventStream().onNext(Event.PosterClick(movieDetail))
            }
        }

        movieTitleTextView.text = movieDetail.originalTitle

        overViewTextView.also {
            it.text = movieDetail.overView

            it.setOnClickListener { view ->
                if (view is TextView) {
                    if (view.ellipsize != null) {
                        view.maxLines = Integer.MAX_VALUE
                        view.ellipsize = null
                    } else {
                        view.maxLines = getInteger(R.integer.overviewMaxLength)
                        view.ellipsize = TextUtils.TruncateAt.END
                    }
                }
            }
        }

        ratingTextView.appendTextWithColor(
            " ${movieDetail.movieRating}",
            getColor(R.color.textColor)
        )
        releaseDateTextView.appendTextWithColor(
            " ${movieDetail.releaseData}",
            getColor(R.color.textColor)
        )
    }
}