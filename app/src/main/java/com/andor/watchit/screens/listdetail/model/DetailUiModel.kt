package com.andor.watchit.screens.listdetail.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailUiModel(
    val posterPath: String? = null,
    val posterTitle: String? = null,
    val InfoTitle: String? = null,
    val InfoText: String? = null,
    val listOfDetails: Map<String, String> = mapOf()
) : Parcelable
