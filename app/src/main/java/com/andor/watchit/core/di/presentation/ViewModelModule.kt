package com.andor.watchit.core.di.presentation

import androidx.lifecycle.ViewModel
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.topratedmovielist.topratedmoviemodel.TopRatedMovieListViewModel
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
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
    fun provideTopRatedMovieViewModel(topRatedMovieUseCase: TopRatedMovieUseCase): ViewModel {
        return TopRatedMovieListViewModel(
            topRatedMovieUseCase
        )
    }
}