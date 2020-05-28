package com.andor.watchit.core.di.screen

import com.andor.watchit.core.di.common.ScreenScope
import com.andor.watchit.screens.moviedetail.controller.MovieDetailFragment
import com.andor.watchit.screens.networkerror.controller.NetworkErrorFragment
import com.andor.watchit.screens.posterview.controller.PosterFragment
import com.andor.watchit.screens.searchmovie.controller.SearchMovieFragment
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        ScreenModule::class
    ]
)
interface TopRatedMovieListFragmentComponent : AndroidInjector<TopRatedMovieListFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<TopRatedMovieListFragment>
}

@ScreenScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        ScreenModule::class
    ]
)
interface MovieDetailFragmentComponent : AndroidInjector<MovieDetailFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MovieDetailFragment>
}

@ScreenScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        ScreenModule::class
    ]
)
interface NetworkErrorFragmentComponent : AndroidInjector<NetworkErrorFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<NetworkErrorFragment>
}

@ScreenScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        ScreenModule::class
    ]
)
interface PosterFragmentComponent : AndroidInjector<PosterFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PosterFragment>
}

@ScreenScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        ScreenModule::class
    ]
)
interface SearchMovieFragmentComponent : AndroidInjector<SearchMovieFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<SearchMovieFragment>
}
