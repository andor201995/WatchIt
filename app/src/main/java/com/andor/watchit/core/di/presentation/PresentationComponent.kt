package com.andor.watchit.core.di.presentation

import com.andor.watchit.screens.networkerror.controller.NetworkErrorFragment
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragment
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(topRatedMovieListFragment: TopRatedMovieListFragment)
    fun inject(networkErrorFragment: NetworkErrorFragment)
}