package com.andor.watchit.core.framework

import com.andor.watchit.usecase.movie.TopRatedMovieUseCase
import com.andor.watchit.usecase.search.FindMovieUseCase
import com.andor.watchit.usecase.tv.PopularTvUseCase

data class UseCases(
    val findMovieUseCase: FindMovieUseCase,
    val topRatedMovieUseCase: TopRatedMovieUseCase,
    val popularTvUseCase: PopularTvUseCase
)
