package com.andor.watchit.screens.moviedetail.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
    override fun setMovieDetails(movieDetail: TopRatedMovie) {
        posterImageView.also {
            ViewCompat.setTransitionName(it, movieDetail.posterPath)
            picasso
                .load("${Constants.BASE_IMAGE_URL}/${Constants.IMAGE_SIZE}/${movieDetail.posterPath}")
                .placeholder(ContextCompat.getDrawable(context, R.drawable.ic_image_24px)!!)
                .error(ContextCompat.getDrawable(context, R.drawable.ic_error_24px)!!)
                .into(it)
        }

        movieTitleTextView.text = movieDetail.originalTitle

        overViewTextView.also {
            it.append(getCustomSpannable("\n${movieDetail.overView}", Color.WHITE))

            it.setOnClickListener { view ->
                if (view is TextView) {
                    if (view.ellipsize != null) {
                        view.maxLines = Integer.MAX_VALUE
                        view.ellipsize = null
                    } else {
                        view.maxLines = 2
                        view.ellipsize = TextUtils.TruncateAt.END
                    }
                }
            }
        }

        ratingTextView.append(
            getCustomSpannable(
                " ${movieDetail.movieRating}",
                Color.WHITE
            )
        )
        releaseDateTextView.append(getCustomSpannable(" ${movieDetail.releaseData}", Color.WHITE))
    }

    private fun getCustomSpannable(value: String, color: Int): Spannable {
        val word: Spannable = SpannableString(value)
        word.setSpan(
            ForegroundColorSpan(color),
            0,
            word.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return word
    }
}