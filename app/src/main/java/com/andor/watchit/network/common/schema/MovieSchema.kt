package com.andor.watchit.network.common.schema

data class MovieSchema(
    val page: Int = 0,
    val results: List<Result> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)
