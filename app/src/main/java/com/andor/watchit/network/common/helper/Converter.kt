package com.andor.watchit.network.common.helper

import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.usecase.common.model.GeneralMovie
import java.util.regex.Matcher
import java.util.regex.Pattern

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
                    releaseDate = it.release_date
                )
            )
        }
        return listOfTopRatedMovie
    }

    fun convertFrom(query: String): String {
        val pattern: Pattern = Pattern.compile("\\s+")
        val mat: Matcher = pattern.matcher(query)
        val result = mat.replaceAll("+")
        return result.removePrefix("+").removeSuffix("+")
    }

}