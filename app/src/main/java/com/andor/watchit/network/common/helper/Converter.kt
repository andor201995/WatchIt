package com.andor.watchit.network.common.helper

import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.usecase.common.datasource.GeneralMovie

object Converter {
    fun convertFrom(topRatedMovieSchema: TopRatedMovieSchema): List<GeneralMovie> {
        val listOfTopRatedMovie = ArrayList<GeneralMovie>()
        topRatedMovieSchema.results.forEach {
            listOfTopRatedMovie.add(
                GeneralMovie(
                    originalTitle = it.original_title,
                    posterPath = it.poster_path,
                    movieId = it.id,
                    movieRating = it.vote_average,
                    overView = it.overview,
                    releaseData = it.release_date
                )
            )
        }
        return listOfTopRatedMovie
    }

}