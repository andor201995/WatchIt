package com.andor.watchit.usecase.common.model

import android.os.Parcelable
import com.andor.watchit.screens.listdetail.model.DetailUiModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieUiModel(
    val originalTitle: String?,
    val posterPath: String?,
    val movieId: Int,
    val overView: String?,
    val releaseDate: String?,
    val movieRating: Double?
) : Parcelable

fun MovieUiModel.toDetailModel(): DetailUiModel {
    val detailMap = mutableMapOf<String, String>().apply {
        releaseDate.takeIf { it.isNullOrBlank().not() }?.let {
            put("Release Date :", it)
        }
        movieRating.takeIf { it != null }?.let {
            put("Movie Rating :", movieRating.toString())
        }
    }

    return DetailUiModel(
        posterPath,
        originalTitle,
        "OverView",
        overView,
        detailMap
    )
}
