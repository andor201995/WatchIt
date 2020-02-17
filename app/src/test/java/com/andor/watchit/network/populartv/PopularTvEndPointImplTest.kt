package com.andor.watchit.network.populartv

import com.andor.watchit.helper.MockServerRetrofit
import com.andor.watchit.helper.TestData
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class PopularTvEndPointImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    private var mMockWebServer = MockWebServer()

    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: PopularTvEndPoint

    @Before
    fun setup() {
        mMockWebServer.start()
        val tvApi = MockServerRetrofit.getTvApi(mMockWebServer)
        systemUT = PopularTvEndPointImpl(tvApi)
        // initial state setup
    }

    @After
    fun tearDown() {
        mMockWebServer.shutdown()
    }

    // correct data sent
    // success and correct data return
    @Test
    fun getPopularMovie_correctData_returnSuccess() {
        //Arrange
        success(TestData.TV_SERVER_RESPONSE_JSON_SUCCESS)
//        Act
        val popularMovies = systemUT.getPopularMovies(1)
//        Assert
        popularMovies.doOnSuccess {
            Assert.assertEquals(TestData.TV_SERVER_RESPONSE_POPULAR_TV_SCHEMA, it)
        }
    }

    // failure
    @Test
    fun getPopularMovie_correctData_returnFailure() {
        //Arrange
        failure()
        //Act
        val popularMovies = systemUT.getPopularMovies(1)
        //Assert
        popularMovies.doOnError {
            // if error occur's
            assert(true)
        }
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
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
        mMockWebServer.enqueue(response)
    }
    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}