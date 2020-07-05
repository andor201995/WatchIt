package com.andor.watchit.network.common.schema

/**
 * This class holds network result from movie api
 *
 * @property page Int page number for the Movie
 * @property results List<Result> List of movies in the page
 * @property total_pages Int
 * @property total_results Int
 */
data class MovieSchema(
    val page: Int = 0,
    val results: List<Result> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)
