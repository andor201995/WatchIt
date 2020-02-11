package com.andor.watchit.usecase.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeneralMovie(
    val originalTitle: String,
    val posterPath: String,
    val movieId: Int,
    val overView: String,
    val releaseData: String,
    val movieRating: Double
) : Parcelable