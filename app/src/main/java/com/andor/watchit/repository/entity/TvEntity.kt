package com.andor.watchit.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andor.watchit.usecase.common.model.TvUiModel

@Entity(tableName = TvDbContract.TABLE_NAME)
data class TvEntity(
    @ColumnInfo(name = TvDbContract.ORIGINAL_TITLE)
    val originalTitle: String?,
    @ColumnInfo(name = TvDbContract.POSTER_PATH)
    val posterPath: String?,
    @PrimaryKey
    @ColumnInfo(name = TvDbContract.TV_ID)
    val tvId: Int,
    @ColumnInfo(name = TvDbContract.OVERVIEW)
    val overView: String?,
    @ColumnInfo(name = TvDbContract.FIRST_AIR_DATE)
    val firstAirDate: String?,
    @ColumnInfo(name = TvDbContract.RATING)
    val rating: Double?
) {
    companion object {
        fun fromTvUiModel(model: TvUiModel) =
            with(model) {
                TvEntity(
                    originalTitle,
                    posterPath,
                    tvId,
                    overView,
                    firstAirDate,
                    tvRating
                )
            }
    }

    fun toTvUiModel() = TvUiModel(
        this.originalTitle,
        this.posterPath,
        this.tvId,
        this.overView,
        this.firstAirDate,
        this.rating
    )
}

object TvDbContract {
    const val TABLE_NAME = "tv"
    const val TV_ID = "tv_id"
    const val RATING = "rating"
    const val ORIGINAL_TITLE = "original_title"
    const val OVERVIEW = "overview"
    const val FIRST_AIR_DATE = "first_air_date"
    const val POSTER_PATH = "poster_path"
}
