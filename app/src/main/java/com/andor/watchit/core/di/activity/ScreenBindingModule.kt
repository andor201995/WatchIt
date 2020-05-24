package com.andor.watchit.core.di.activity

import androidx.fragment.app.Fragment
import com.andor.watchit.core.di.common.ScreenKey
import com.andor.watchit.core.di.screen.*
import com.andor.watchit.screens.moviedetail.controller.MovieDetailFragment
import com.andor.watchit.screens.networkerror.controller.NetworkErrorFragment
import com.andor.watchit.screens.posterview.controller.PosterFragment
import com.andor.watchit.screens.searchmovie.controller.SearchMovieFragment
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [
        TopRatedMovieListFragmentComponent::class,
        MovieDetailFragmentComponent::class,
        NetworkErrorFragmentComponent::class,
        PosterFragmentComponent::class,
        SearchMovieFragmentComponent::class
    ]
)
abstract class ScreenBindingModule {

    @Binds
    @IntoMap
    @ScreenKey(TopRatedMovieListFragment::class)
    abstract fun bindTopRatedMovieListFragment(factory: TopRatedMovieListFragmentComponent.Factory): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @ScreenKey(MovieDetailFragment::class)
    abstract fun bindMovieDetailFragment(factory: MovieDetailFragmentComponent.Factory): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @ScreenKey(NetworkErrorFragment::class)
    abstract fun bindNetworkErrorFragment(factory: NetworkErrorFragmentComponent.Factory): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @ScreenKey(PosterFragment::class)
    abstract fun bindPosterFragment(factory: PosterFragmentComponent.Factory): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @ScreenKey(SearchMovieFragment::class)
    abstract fun bindTopRatedMovieScreen(factory: SearchMovieFragmentComponent.Factory): AndroidInjector.Factory<out Fragment>
}