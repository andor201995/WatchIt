package com.andor.watchit.usecase.common.model

import android.os.Parcelable
import com.andor.watchit.screens.listdetail.model.DetailUiModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvUiModel(
    val originalTitle: String?,
    val posterPath: String?,
    val tvId: Int,
    val overView: String?,
    val firstAirDate: String?,
    val tvRating: Double?
) : Parcelable

fun TvUiModel.toDetailModel(): DetailUiModel {
    val detailMap = mutableMapOf<String, String>().apply {
        firstAirDate.takeIf { it.isNullOrBlank().not() }?.let {
            put("First Air Date :", it)
        }
        tvRating.takeIf { it != null }?.let {
            put("Tv Rating :", tvRating.toString())
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
