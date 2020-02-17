package com.andor.watchit.network.common.helper

import com.andor.watchit.network.common.schema.GeneralTvSchema
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.andor.watchit.usecase.common.model.GeneralTv
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
                    releaseData = it.release_date
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

    fun convertFrom(generalTvSchema: GeneralTvSchema): ArrayList<GeneralTv> {
        val listOfPopularTv = ArrayList<GeneralTv>()
        generalTvSchema.results.forEach {
            listOfPopularTv.add(
                GeneralTv(
                    originalTitle = it.original_name,
                    posterPath = it.poster_path,
                    tvId = it.id,
                    tvRating = it.vote_average,
                    overView = it.overview,
                    firstAirDate = it.first_air_date
                )
            )
        }
        return listOfPopularTv
    }

}