package com.andor.watchit.network.endpoints.search

import com.andor.watchit.helper.MockServerRetrofit
import com.andor.watchit.helper.TestData
import com.andor.watchit.network.search.FindMovieEndPoint
import com.andor.watchit.network.search.FindMovieEndPointImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import java.net.HttpURLConnection
import okhttp3.mockwebserver.MockResponse
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
        success(TestData.SERVER_RESPONSE_JSON_SUCCESS)
        // Act
        systemUT.findMovieAndNotify(VALID_PAGE_NUMBER, VALID_QUERY, listener)
        // Assert
        verify(listener, times(1)).onFetchSuccess(TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA)
    }

    // fetch failure with no data and error msg
    @Test
    fun findMovie_fetchFail_nullData() {
        // Arrange
        success(TestData.SERVER_RESPONSE_JSON_SUCCESS_NULL)
        // Act
        systemUT.findMovieAndNotify(VALID_PAGE_NUMBER, VALID_QUERY, listener)
        // Assert
        verify(listener, times(0)).onFetchSuccess(any())
        verify(listener, times(1)).onFetchFailed()
    }

    @Test
    fun findMovie_fetchNetworkFail_nullData() {
        // Arrange
        failure()
        // Act
        systemUT.findMovieAndNotify(VALID_PAGE_NUMBER, VALID_QUERY, listener)
        // Assert
        verify(listener, times(0)).onFetchSuccess(any())
        verify(listener, times(1)).onFetchFailed()
    }

    // region helper methods -----------------------------------------------------------------------
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
