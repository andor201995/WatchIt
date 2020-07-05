package com.andor.watchit.network.endpoints.search

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
class FindMovieEndPointImplTest {
    // region constants ----------------------------------------------------------------------------
    val VALID_PAGE_NUMBER = 1
    val VALID_QUERY = "TEST QUERY"
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    @Mock
    lateinit var listener: FindMovieEndPoint.Listener

    private var mMockWebServer = MockWebServer()

    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: FindMovieEndPoint

    @Before
    fun setup() {
        mMockWebServer.start()
        val movieApi = MockServerRetrofit.getMovieApi(mMockWebServer)

        systemUT = FindMovieEndPointImpl(movieApi)
        // initial state setup
    }

    @After
    fun tearDown() {
        mMockWebServer.shutdown()
    }

    // fetch success with correct data
    @Test
    fun findMovie_fetchSuccess_validData() {
        // Arrange
        MockServerRetrofit.webServerSuccess(mMockWebServer, TestData.SERVER_RESPONSE_JSON_SUCCESS)
        // Act
        systemUT.findMovieAndNotify(VALID_PAGE_NUMBER, VALID_QUERY, listener)
        // Assert
        verify(listener, times(1)).onFetchSuccess(
            TestData.SERVER_RESPONSE_MOVIE_SCHEMA
        )
    }

    // fetch failure with no data and error msg
    @Test
    fun findMovie_fetchFail_nullData() {
        // Arrange
        MockServerRetrofit.webServerSuccess(
            mMockWebServer,
            TestData.SERVER_RESPONSE_JSON_SUCCESS_NULL
        )
        // Act
        systemUT.findMovieAndNotify(VALID_PAGE_NUMBER, VALID_QUERY, listener)
        // Assert
        verify(listener, times(0)).onFetchSuccess(any())
        verify(listener, times(1)).onFetchFailed()
    }

    @Test
    fun findMovie_fetchNetworkFail_nullData() {
        // Arrange
        MockServerRetrofit.webServerFailure(mMockWebServer)
        // Act
        systemUT.findMovieAndNotify(VALID_PAGE_NUMBER, VALID_QUERY, listener)
        // Assert
        verify(listener, times(0)).onFetchSuccess(any())
        verify(listener, times(1)).onFetchFailed()
    }
}
