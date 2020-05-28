package com.andor.watchit.core.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andor.watchit.usecase.common.model.GeneralMovie

@Entity(tableName = "movie")
data class MovieEntity(
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "overview")
    val overView: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "rating")
    val movieRating: Double?
) {
    companion object {
        fun fromGeneralMovie(generalMovie: GeneralMovie) = MovieEntity(
            generalMovie.originalTitle,
            generalMovie.posterPath,
            generalMovie.movieId,
            generalMovie.overView,
            generalMovie.releaseDate,
            generalMovie.movieRating
        )
    }

    fun toGeneralMovie() = GeneralMovie(
        this.originalTitle,
        this.posterPath,
        this.movieId,
        this.overView,
        this.releaseDate,
        this.movieRating
    )
}
