package com.andor.watchit.core.di.application

import com.andor.watchit.core.utils.Constants
import com.andor.watchit.network.api.MovieApi
import com.andor.watchit.network.api.TvApi
import com.andor.watchit.network.common.helper.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRetroFit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideAuthInterceptor() = AuthInterceptor()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(
        MovieApi::class.java)

    @Provides
    fun provideTvApi(retrofit: Retrofit): TvApi = retrofit.create(
        TvApi::class.java)

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}
