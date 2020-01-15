package com.andor.watchit.network

import com.andor.watchit.helper.CurrentThreadExecutor
import com.andor.watchit.helper.TestData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class TopRatedMovieListEndPointImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    @Mock
    lateinit var listener: TopRatedMovieListEndPoint.Listener

    private var mMockWebServer = MockWebServer()

    // end region helper fields --------------------------------------------------------------------

    internal lateinit var systemUT: TopRatedMovieListEndPoint

    @Before
    fun setup() {
        mMockWebServer.start()
        val movieApi = setUpMovieApi()

        systemUT = TopRatedMovieListEndPointImpl(movieApi)
        // initial state setup
    }

    @After
    fun teardown() {
        mMockWebServer.shutdown()
    }

    // fetch success with correct data
    @Test
    fun onFetchTopRatedMovieListAndNotify_fetchSuccess_validData() {
        //Arrange
        success(TestData.SERVER_RESPONSE_JSON_SUCCESS)
        //Act
        systemUT.onFetchTopRatedMovieListAndNotify(listener)
        //Assert
        verify(listener, times(1)).onFetchSuccess(TestData.SERVER_RESPONSE_WEATHER_SCHEMA)
    }


    // fetch failure with no data and error msg
    @Test
    fun onFetchTopRatedMovieListAndNotify_fetchFail_nullData() {
        //Arrange
        success(TestData.SERVER_RESPONSE_JSON_SUCCESS_NULL)
        //Act
        systemUT.onFetchTopRatedMovieListAndNotify(listener)
        //Assert
        verify(listener, times(0)).onFetchSuccess(any())
        verify(listener, times(1)).onFetchFailed()

    }

    @Test
    fun onFetchTopRatedMovieListAndNotify_fetchNetworFail_nullData() {
        //Arrange
        failure()
        //Act
        systemUT.onFetchTopRatedMovieListAndNotify(listener)
        //Assert
        verify(listener, times(0)).onFetchSuccess(any())
        verify(listener, times(1)).onFetchFailed()

    }

    // region helper methods -----------------------------------------------------------------------

    private fun setUpMovieApi(): MovieApi {
        val okHttpClient: OkHttpClient = getOkHttpWithCustomDispatcher()
        return Retrofit.Builder()
            .baseUrl(mMockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }

    private fun getOkHttpWithCustomDispatcher(): OkHttpClient {
        val currentThreadExecutor = CurrentThreadExecutor()
        val dispatcher = Dispatcher(currentThreadExecutor)
        return OkHttpClient().newBuilder().dispatcher(dispatcher).build()
    }

    private fun success(data: String) {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(data)
        mMockWebServer.enqueue(response)
    }

    private fun failure() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_GATEWAY_TIMEOUT)
        mMockWebServer.enqueue(response)
    }

    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}