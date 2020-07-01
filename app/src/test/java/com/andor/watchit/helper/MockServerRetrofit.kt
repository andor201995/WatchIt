package com.andor.watchit.helper

import com.andor.watchit.network.api.MovieApi
import com.andor.watchit.network.api.TvApi
import java.net.HttpURLConnection
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MockServerRetrofit {

    fun getMovieApi(mMockWebServer: MockWebServer): MovieApi {
        val okHttpClient: OkHttpClient = getOkHttpWithCustomDispatcher()
        return Retrofit.Builder()
            .baseUrl(mMockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }

    fun getTvApi(mMockWebServer: MockWebServer): TvApi {
        val okHttpClient: OkHttpClient = getOkHttpWithCustomDispatcher()
        return Retrofit.Builder()
            .baseUrl(mMockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TvApi::class.java)
    }

    private fun getOkHttpWithCustomDispatcher(): OkHttpClient {
        val currentThreadExecutor = CurrentThreadExecutor()
        val dispatcher = Dispatcher(currentThreadExecutor)
        return OkHttpClient().newBuilder().dispatcher(dispatcher).build()
    }

    fun webServerSuccess(mockWebServer: MockWebServer, data: String) {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(data)
        mockWebServer.enqueue(response)
    }

    fun webServerFailure(mockWebServer: MockWebServer) {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT)
        mockWebServer.enqueue(response)
    }
}
