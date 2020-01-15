package com.andor.watchit.usecase.topratedmovie

import com.andor.watchit.core.Convertor
import com.andor.watchit.helper.TestData
import com.andor.watchit.network.endpoints.TopRatedMovieListEndPoint
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.observers.TestObserver
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

    //fetch data return success response
    @Test
    fun fetchTopRatedMovieAndNotify_success_returnValidData() {
        //Arrange
        success()
        //Act
        systemUT.getResultStream().subscribe(testObserver)
        systemUT.fetchTopRatedMovieAndNotify()
        //Assert
        verify(mTopRatedMovieListEndPointMock).onFetchTopRatedMovieListAndNotify(systemUT)

        testObserver.assertValue {
            it == TopRatedMovieUseCaseImpl.FetchResult.Success(Convertor.convertFrom(TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA))
        }

    }

    //fetch data return error response
    @Test
    fun fetchTopRatedMovieAndNotify_Failure_retrunFailure() {
        //Arrange
        failure()
        //Act
        systemUT.getResultStream().subscribe(testObserver)
        systemUT.fetchTopRatedMovieAndNotify()
        //Assert
        verify(mTopRatedMovieListEndPointMock).onFetchTopRatedMovieListAndNotify(systemUT)
        testObserver.assertValue {
            it is TopRatedMovieUseCaseImpl.FetchResult.Failure
        }
    }

    // region helper methods -----------------------------------------------------------------------

    private fun failure() {
        whenever(mTopRatedMovieListEndPointMock.onFetchTopRatedMovieListAndNotify(any())).thenAnswer {
            val listener = it.getArgument(0) as TopRatedMovieListEndPoint.Listener
            listener.onFetchFailed()
            listener
        }
    }

    private fun success() {
        whenever(mTopRatedMovieListEndPointMock.onFetchTopRatedMovieListAndNotify(any())).thenAnswer {
            val listener = it.getArgument(0) as TopRatedMovieListEndPoint.Listener
            listener.onFetchSuccess(TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA)
            listener
        }
    }

    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}