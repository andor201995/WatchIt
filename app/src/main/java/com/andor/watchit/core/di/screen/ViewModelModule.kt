package com.andor.watchit.core.di.screen

import androidx.lifecycle.ViewModel
import com.andor.watchit.core.di.utils.ViewModelKey
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.movielist.controller.MovieListViewModel
import com.andor.watchit.screens.searchmovie.controller.SearchMovieViewModel
import com.andor.watchit.screens.tvlist.controller.TvListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(
    includes = [
        ViewModelModule.Binding::class
    ]
)
class ViewModelModule {
    @Provides
    fun viewModelFactory(
        providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Module
    internal interface Binding {
        @Binds
        @IntoMap
        @ViewModelKey(MovieListViewModel::class)
        fun bindTopRatedMovieViewModel(
            movieListViewModel: MovieListViewModel
        ): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(SearchMovieViewModel::class)
        fun bindSearchViewModel(searchMovieViewModel: SearchMovieViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(TvListViewModel::class)
        fun bindTvListViewModel(tvListViewModel: TvListViewModel): ViewModel
    }
}
