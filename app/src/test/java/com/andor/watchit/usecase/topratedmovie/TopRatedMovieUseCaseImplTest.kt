package com.andor.watchit.usecase.topratedmovie

import com.andor.watchit.helper.TestData
import com.andor.watchit.network.endpoints.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.network.helper.Converter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopRatedMovieUseCaseImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    @Mock
    lateinit var mTopRatedMovieListEndPointMock: TopRatedMovieListEndPoint
    private val testObserver = TestObserver<TopRatedMovieUseCaseImpl.FetchResult>()
    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: TopRatedMovieUseCaseImpl

    @Before
    fun setup() {
        systemUT = TopRatedMovieUseCaseImpl(mTopRatedMovieListEndPointMock)
        // initial state setup
    }

    @After
    fun tearDown() {
        testObserver.dispose()
    }

    //fetch data return success response
    @Test
    fun fetchTopRatedMovieAndNotify_success_returnValidData() {
        //Arrange
        success()
        //Act
        systemUT.fetchTopRatedMovieAndNotify(1).subscribe(testObserver)
        //Assert
        verify(mTopRatedMovieListEndPointMock).onFetchTopRatedMovieListAndNotify(
            any(),
            any()
        )
        val testServerResponse = TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA
        testObserver.assertValue {
            it == TopRatedMovieUseCaseImpl.FetchResult.Success(
                Converter.convertFrom(testServerResponse),
                testServerResponse.page,
                testServerResponse.total_pages
            )
        }

    }

    //fetch data return error response
    @Test
    fun fetchTopRatedMovieAndNotify_Failure_retrunFailure() {
        //Arrange
        failure()
        //Act
        systemUT.fetchTopRatedMovieAndNotify(1).subscribe(testObserver)
        //Assert
        verify(mTopRatedMovieListEndPointMock).onFetchTopRatedMovieListAndNotify(
            any(),
            any()
        )
        testObserver.assertValue {
            it is TopRatedMovieUseCaseImpl.FetchResult.Failure
        }
    }

    // region helper methods -----------------------------------------------------------------------

    private fun failure() {
        whenever(
            mTopRatedMovieListEndPointMock.onFetchTopRatedMovieListAndNotify(
                any(),
                any()
            )
        ).thenAnswer {
            val listener = it.getArgument(1) as TopRatedMovieListEndPoint.Listener
            listener.onFetchFailed()
            listener
        }
    }

    private fun success() {
        whenever(
            mTopRatedMovieListEndPointMock.onFetchTopRatedMovieListAndNotify(
                any(),
                any()
            )
        ).thenAnswer {
            val listener = it.getArgument(1) as TopRatedMovieListEndPoint.Listener
            listener.onFetchSuccess(TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA)
            listener
        }
    }

    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}