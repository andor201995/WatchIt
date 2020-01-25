package com.andor.watchit.network.common.schema

data class TopRatedMovieSchema(
    val page: Int = 0,
    val results: List<Result> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)