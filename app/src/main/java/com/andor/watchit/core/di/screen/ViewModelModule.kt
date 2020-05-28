package com.andor.watchit.core.di.screen

import androidx.lifecycle.ViewModel
import com.andor.watchit.core.di.common.ViewModelKey
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.searchmovie.model.SearchMovieViewModel
import com.andor.watchit.screens.topratedmovielist.model.TopRatedMovieListViewModel
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
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Module
    internal interface Binding {
        @Binds
        @IntoMap
        @ViewModelKey(TopRatedMovieListViewModel::class)
        fun bindTopRatedMovieViewModel(topRatedMovieListViewModel: TopRatedMovieListViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(SearchMovieViewModel::class)
        fun bindSearchViewModel(searchMovieViewModel: SearchMovieViewModel): ViewModel
    }
}
