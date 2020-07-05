package com.andor.watchit.screens.listdetail.view

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.andor.watchit.R
import com.andor.watchit.core.extensions.appendTextWithColor
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.listdetail.model.DetailUiModel
import com.andor.watchit.screens.listdetail.model.DetailViewEvent

interface MovieDetailViewMvc : ObservableViewMvc<DetailViewEvent> {
    fun setDetails(detailModel: DetailUiModel)
}

class MovieDetailViewMvcImpl(
    parent: ViewGroup?,
    private val inflater: LayoutInflater,
    private val imageLoader: ImageLoader
) : MovieDetailViewMvc, BaseObservableViewMvc<DetailViewEvent>() {
    private var overViewTextView: TextView
    private var movieTitleTextView: TextView
    private var posterImageView: ImageView
    private var detailContainer: LinearLayoutCompat

    init {
        setRootView(inflater.inflate(R.layout.movie_detail_fragment, parent, false))
        posterImageView = findViewById(R.id.movieDetailPosterImageView)
        movieTitleTextView = findViewById(R.id.posterTitle)
        overViewTextView = findViewById(R.id.infoDetailText)
        detailContainer = findViewById(R.id.detailContainer)
    }

    @SuppressLint("SetTextI18n")
    override fun setDetails(detailModel: DetailUiModel) {
        posterImageView.also {
            imageLoader.loadImageInto(it, detailModel.posterPath)
            it.setOnClickListener {
                getEventStream().onNext(DetailViewEvent.PosterClick(detailModel))
            }
        }

        movieTitleTextView.text = detailModel.posterTitle

        overViewTextView.also {
            it.text = detailModel.InfoText

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

        for (entry in detailModel.listOfDetails) {
            val textView =
                inflater.inflate(
                    R.layout.dynamic_detail_text_view,
                    detailContainer,
                    false
                ) as TextView
            textView.text = entry.key
            textView.appendTextWithColor(
                " ${entry.value}",
                getAttrColor(R.attr.app_color_text)
            )
            detailContainer.addView(textView)
        }
    }
}
