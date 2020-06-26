package com.andor.watchit.core.framework

import com.andor.watchit.usecase.findmovie.FindMovieUseCase
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import com.andor.watchit.usecase.tv.PopularTvUseCase

data class UseCases(
    val findMovieUseCase: FindMovieUseCase,
    val topRatedMovieUseCase: TopRatedMovieUseCase,
    val popularTvUseCase: PopularTvUseCase
)
