package com.andor.watchit.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andor.watchit.usecase.common.model.GeneralMovie

@Entity(tableName = MovieDbContract.TABLE_NAME)
data class MovieEntity(
    @ColumnInfo(name = MovieDbContract.ORIGINAL_TITLE)
    val originalTitle: String?,
    @ColumnInfo(name = MovieDbContract.POSTER_PATH)
    val posterPath: String?,
    @PrimaryKey
    @ColumnInfo(name = MovieDbContract.MOVIE_ID)
    val movieId: Int,
    @ColumnInfo(name = MovieDbContract.OVERVIEW)
    val overView: String?,
    @ColumnInfo(name = MovieDbContract.RELEASE_DATE)
    val releaseDate: String?,
    @ColumnInfo(name = MovieDbContract.RATING)
    val movieRating: Double?
) {
    companion object {
        fun fromGeneralMovie(generalMovie: GeneralMovie) =
            MovieEntity(
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

object MovieDbContract {
    const val TABLE_NAME = "movie"
    const val MOVIE_ID = "movie_id"
    const val RATING = "rating"
    const val ORIGINAL_TITLE = "original_title"
    const val OVERVIEW = "overview"
    const val RELEASE_DATE = "release_date"
    const val POSTER_PATH = "poster_path"
}
