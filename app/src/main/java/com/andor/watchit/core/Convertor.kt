package com.andor.watchit.core

import com.andor.watchit.network.schema.TopRatedMovieSchema
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

object Convertor {
    fun convertFrom(topRatedMovieSchema: TopRatedMovieSchema): List<TopRatedMovie> {
        val listOfTopRatedMovie = ArrayList<TopRatedMovie>()
        topRatedMovieSchema.results.forEach {
            listOfTopRatedMovie.add(
                TopRatedMovie(
                    originalTitle = it.original_title
                )
            )
        }
        return listOfTopRatedMovie
    }

}