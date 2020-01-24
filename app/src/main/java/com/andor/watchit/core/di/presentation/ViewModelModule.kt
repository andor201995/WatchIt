package com.andor.watchit.core.di.presentation

import androidx.lifecycle.ViewModel
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.searchmovie.model.SearchMovieViewModel
import com.andor.watchit.screens.topratedmovielist.model.TopRatedMovieListViewModel
import com.andor.watchit.usecase.findmovie.FindMovieDataSourceFactory
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieDataSourceFactory
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
class ViewModelModule {
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(TopRatedMovieListViewModel::class)
    fun provideTopRatedMovieViewModel(
        topRatedMovieDataSourceFactory: TopRatedMovieDataSourceFactory
    ): ViewModel {
        return TopRatedMovieListViewModel(
            topRatedMovieDataSourceFactory
        )
    }

    @Provides
    @IntoMap
    @ViewModelKey(SearchMovieViewModel::class)
    fun provideSearchViewModel(findMovieDataSourceFactory: FindMovieDataSourceFactory): ViewModel {
        return SearchMovieViewModel(findMovieDataSourceFactory)
    }
}