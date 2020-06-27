package com.andor.watchit.usecase.topratedmovie

import com.andor.watchit.helper.TestData
import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.repository.movie.MovieRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.observers.TestObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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

    @Mock
    lateinit var mMovieRepository: MovieRepository
    private val testObserver = TestObserver<TopRatedMovieUseCaseImpl.FetchResult>()
    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: TopRatedMovieUseCaseImpl

    @Before
    fun setup() {
        systemUT = TopRatedMovieUseCaseImpl(mTopRatedMovieListEndPointMock, mMovieRepository)
        // initial state setup
        runBlocking {
            whenever(mMovieRepository.addAllMovie(any())).thenReturn(Unit)
        }
    }

    @After
    fun tearDown() {
        testObserver.dispose()
    }

    @Test
    fun fetchTopRatedMovieAndNotify_success_returnValidData() {
        // Arrange
        success()
        // Act
        systemUT.fetchTopRatedMovieAndNotify(1).subscribe(testObserver)
        // Assert
        verify(mTopRatedMovieListEndPointMock).onFetchTopRatedMovieListAndNotify(
            any(),
            any()
        )
        val testServerResponse = TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA
        testObserver.assertValue {
            it == TopRatedMovieUseCaseImpl.FetchResult.Success(
                Converter.convertFrom(testServerResponse),
                testServerResponse.page,
                testServerResponse.total_pages,
                testServerResponse.total_results
            )
        }
    }

    @Test
    fun fetchTopRatedMovieAndNotify_Failure_returnFailure() {
        // Arrange
        runBlocking {
            whenever(mMovieRepository.getMovieCount()).thenReturn(0)
            failure()
            // Act
            systemUT.fetchTopRatedMovieAndNotify(1).subscribe(testObserver)
            // Assert
            delay(200)
            verify(mTopRatedMovieListEndPointMock).onFetchTopRatedMovieListAndNotify(
                any(),
                any()
            )
            testObserver.assertValue {
                it is TopRatedMovieUseCaseImpl.FetchResult.Failure
            }
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
