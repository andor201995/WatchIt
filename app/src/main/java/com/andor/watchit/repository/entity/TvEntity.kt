package com.andor.watchit.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andor.watchit.usecase.common.model.TvUiModel

@Entity(tableName = "tv")
data class TvEntity(
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @PrimaryKey
    @ColumnInfo(name = "tv_id")
    val tvId: Int,
    @ColumnInfo(name = "overview")
    val overView: String?,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String?,
    @ColumnInfo(name = "rating")
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
