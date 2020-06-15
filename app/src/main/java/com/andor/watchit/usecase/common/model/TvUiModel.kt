package com.andor.watchit.usecase.common.model

import android.os.Parcelable
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
