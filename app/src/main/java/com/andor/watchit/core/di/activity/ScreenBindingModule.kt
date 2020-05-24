package com.andor.watchit.core.di.activity

import androidx.fragment.app.Fragment
import com.andor.watchit.core.di.common.ScreenKey
import com.andor.watchit.core.di.screen.TopRatedMovieListComponent
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [
        TopRatedMovieListComponent::class
    ]
)
abstract class ScreenBindingModule {

    @Binds
    @IntoMap
    @ScreenKey(TopRatedMovieListFragment::class)
    abstract fun bindTopRatedMovieScreen(factory: TopRatedMovieListComponent.Factory): AndroidInjector.Factory<out Fragment>
}