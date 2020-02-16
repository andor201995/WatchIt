package com.andor.watchit.network.common.schema

data class GeneralTvSchema(
    val page: Int = 0,
    val results: List<TvItemResult> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)