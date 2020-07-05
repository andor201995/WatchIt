package com.andor.watchit.network.common.helper

import com.andor.watchit.network.common.schema.MovieSchema
import com.andor.watchit.network.common.schema.TvSchema
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.andor.watchit.usecase.common.model.TvUiModel
import java.util.regex.Matcher
import java.util.regex.Pattern

object Converter {
    fun convertFrom(movieSchema: MovieSchema): List<MovieUiModel> {
        val listOfTopRatedMovie = ArrayList<MovieUiModel>()
        movieSchema.results.forEach {
            listOfTopRatedMovie.add(
                MovieUiModel(
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

    fun convertFrom(generalTvSchema: TvSchema): ArrayList<TvUiModel> {
        val listOfPopularTv = ArrayList<TvUiModel>()
        generalTvSchema.results.forEach {
            listOfPopularTv.add(
                TvUiModel(
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

    fun convertFrom(query: String): String {
        val pattern: Pattern = Pattern.compile("\\s+")
        val mat: Matcher = pattern.matcher(query)
        val result = mat.replaceAll("+")
        return result.removePrefix("+").removeSuffix("+")
    }
}
