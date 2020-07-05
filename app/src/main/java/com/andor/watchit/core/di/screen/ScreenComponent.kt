package com.andor.watchit.core.di.screen

import com.andor.watchit.core.di.utils.ScreenScope
import com.andor.watchit.screens.listdetail.controller.ListDetailFragment
import com.andor.watchit.screens.movielist.controller.MovieListFragment
import com.andor.watchit.screens.networkerror.controller.NetworkErrorFragment
import com.andor.watchit.screens.posterview.controller.PosterFragment
import com.andor.watchit.screens.searchmovie.controller.SearchMovieFragment
import com.andor.watchit.screens.tvlist.controller.TvListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        ScreenModule::class
    ]
)
interface MovieListFragmentComponent : AndroidInjector<MovieListFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MovieListFragment>
}

@ScreenScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        ScreenModule::class
    ]
)
interface ListDetailFragmentComponent : AndroidInjector<ListDetailFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<ListDetailFragment>
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

@ScreenScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        ScreenModule::class
    ]
)
interface TvListFragmentComponent : AndroidInjector<TvListFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<TvListFragment>
}
