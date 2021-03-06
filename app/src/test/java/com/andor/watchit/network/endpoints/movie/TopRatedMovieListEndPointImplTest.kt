package com.andor.watchit.network.endpoints.movie

import com.andor.watchit.helper.MockServerRetrofit
import com.andor.watchit.helper.TestData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopRatedMovieListEndPointImplTest {
    // region constants ----------------------------------------------------------------------------

    val VALID_PAGE_NUMBER = 1
    val INVALID_PAGE_NUMBER = 1

    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    @Mock
    lateinit var listener: TopRatedMovieListEndPoint.Listener

    private var mMockWebServer = MockWebServer()

    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: TopRatedMovieListEndPoint

    @Before
    fun setup() {
        mMockWebServer.start()
        val movieApi = MockServerRetrofit.getMovieApi(mMockWebServer)

        systemUT =
            TopRatedMovieListEndPointImpl(
                movieApi
            )
        // initial state setup
    }

    @After
    fun teardown() {
        mMockWebServer.shutdown()
    }

    // fetch success with correct data
    @Test
    fun onFetchTopRatedMovieListAndNotify_fetchSuccess_validData() {
        // Arrange
        MockServerRetrofit.webServerSuccess(mMockWebServer, TestData.SERVER_RESPONSE_JSON_SUCCESS)
        // Act
        systemUT.onFetchTopRatedMovieListAndNotify(VALID_PAGE_NUMBER, listener)
        // Assert
        verify(listener, times(1)).onFetchSuccess(TestData.SERVER_RESPONSE_MOVIE_SCHEMA)
    }

    // fetch failure with no data and error msg
    @Test
    fun onFetchTopRatedMovieListAndNotify_fetchFail_nullData() {
        // Arrange
        MockServerRetrofit.webServerSuccess(
            mMockWebServer,
            TestData.SERVER_RESPONSE_JSON_SUCCESS_NULL
        )
        // Act
        systemUT.onFetchTopRatedMovieListAndNotify(VALID_PAGE_NUMBER, listener)
        // Assert
        verify(listener, times(0)).onFetchSuccess(any())
        verify(listener, times(1)).onFetchFailed()
    }

    @Test
    fun onFetchTopRatedMovieListAndNotify_fetchNetworFail_nullData() {
        // Arrange
        MockServerRetrofit.webServerFailure(mMockWebServer)
        // Act
        systemUT.onFetchTopRatedMovieListAndNotify(VALID_PAGE_NUMBER, listener)
        // Assert
        verify(listener, times(0)).onFetchSuccess(any())
        verify(listener, times(1)).onFetchFailed()
    }
}
