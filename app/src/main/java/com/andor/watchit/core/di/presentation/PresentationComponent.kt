package com.andor.watchit.core.di.presentation

import com.andor.watchit.screens.moviedetail.controller.MovieDetailFragment
import com.andor.watchit.screens.networkerror.controller.NetworkErrorFragment
import com.andor.watchit.screens.posterview.controller.PosterFragment
import com.andor.watchit.screens.searchmovie.controller.SearchMovieFragment
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragment
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(topRatedMovieListFragment: TopRatedMovieListFragment)
    fun inject(networkErrorFragment: NetworkErrorFragment)
    fun inject(movieDetailFragment: MovieDetailFragment)
    fun inject(posterFragment: PosterFragment)
    fun inject(searchMovieFragment: SearchMovieFragment)
}