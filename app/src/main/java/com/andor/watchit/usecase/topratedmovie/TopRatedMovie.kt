package com.andor.watchit.usecase.topratedmovie

import android.os.Parcel
import android.os.Parcelable


data class TopRatedMovie(
    val originalTitle: String?,
    val posterPath: String?,
    val movieId: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(originalTitle)
        parcel.writeString(posterPath)
        parcel.writeValue(movieId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopRatedMovie> {
        override fun createFromParcel(parcel: Parcel): TopRatedMovie {
            return TopRatedMovie(parcel)
        }

        override fun newArray(size: Int): Array<TopRatedMovie?> {
            return arrayOfNulls(size)
        }
    }

}