package com.andor.watchit.core.di.application

import com.andor.watchit.network.api.MovieApi
import com.andor.watchit.network.endpoints.TopRatedMovieListEndPoint
import com.andor.watchit.network.endpoints.TopRatedMovieListEndPointImpl
import com.andor.watchit.network.helper.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetroFit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideTopRatedMovieListEndPoint(movieApi: MovieApi): TopRatedMovieListEndPoint {
        return TopRatedMovieListEndPointImpl(movieApi)
    }
}