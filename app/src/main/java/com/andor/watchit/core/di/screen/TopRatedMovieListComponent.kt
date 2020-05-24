package com.andor.watchit.core.di.screen

import com.andor.watchit.core.di.common.ScreenScope
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent
interface TopRatedMovieListComponent : AndroidInjector<TopRatedMovieListFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<TopRatedMovieListFragment>
}