package com.andor.watchit.network.helper

import com.andor.watchit.network.schema.TopRatedMovieSchema
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

object Converter {
    fun convertFrom(topRatedMovieSchema: TopRatedMovieSchema): List<TopRatedMovie> {
        val listOfTopRatedMovie = ArrayList<TopRatedMovie>()
        topRatedMovieSchema.results.forEach {
            listOfTopRatedMovie.add(
                TopRatedMovie(
                    originalTitle = it.original_title,
                    posterPath = it.poster_path,
                    movieId = it.id
                )
            )
        }
        return listOfTopRatedMovie
    }

}