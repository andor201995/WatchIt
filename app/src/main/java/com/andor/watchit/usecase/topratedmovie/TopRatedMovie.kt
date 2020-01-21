package com.andor.watchit.usecase.topratedmovie

import android.os.Parcel
import android.os.Parcelable


data class TopRatedMovie(
    val originalTitle: String? = "",
    val posterPath: String? = "",
    val movieId: Int = INVALID_MOVIE_ID,
    val overView: String? = "",
    val releaseData: String? = "",
    val movieRating: Double = 0.0

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(originalTitle)
        parcel.writeString(posterPath)
        parcel.writeInt(movieId)
        parcel.writeString(overView)
        parcel.writeString(releaseData)
        parcel.writeDouble(movieRating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopRatedMovie> {
        const val INVALID_MOVIE_ID = -1

        override fun createFromParcel(parcel: Parcel): TopRatedMovie {
            return TopRatedMovie(parcel)
        }

        override fun newArray(size: Int): Array<TopRatedMovie?> {
            return arrayOfNulls(size)
        }
    }
}