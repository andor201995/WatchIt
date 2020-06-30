package com.andor.watchit.usecase.search

import com.andor.watchit.helper.TestData
import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.network.search.FindMovieEndPoint
import com.andor.watchit.repository.movie.MovieRepository
import com.andor.watchit.usecase.search.FindMovieUseCase.FetchResult
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.observers.TestObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindMovieUseCaseImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    private val VALID_QUERY: String = "VALID QUERY"
    private val VALID_PAGE: Int = 1

    // region helper fields ------------------------------------------------------------------------
    @Mock
    private lateinit var mFindMovieEndPointMock: FindMovieEndPoint

    @Mock
    private lateinit var mMovieRepository: MovieRepository
    private val testObserver = TestObserver<FetchResult>()

    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: FindMovieUseCase

    @Before
    fun setup() {
        systemUT = FindMovieUseCaseImpl(mFindMovieEndPointMock, mMovieRepository)
        // initial state setup
    }

    @After
    fun tearDown() {
        testObserver.dispose()
    }

    @Test
    fun findMovieAndNotify_validInput() {
        // Arrange
        val pageAC = argumentCaptor<Int>()
        val queryAC = argumentCaptor<String>()
        // Act
        systemUT.findMovie(VALID_PAGE, VALID_QUERY)
        // Assert
        verify(mFindMovieEndPointMock).findMovieAndNotify(
            pageAC.capture(),
            queryAC.capture(),
            any()
        )
        Assert.assertEquals(VALID_PAGE, pageAC.firstValue)
        Assert.assertEquals(Converter.convertFrom(VALID_QUERY), queryAC.firstValue)
    }

    @Test
    fun findMovieAndNotify_validInput_returnSuccess() {
        // Arrange
        val schema = TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA
        success(schema)
        // Act
        systemUT.findMovie(VALID_PAGE, VALID_QUERY).subscribe(testObserver)
        // Assert
        testObserver.assertValue {
            it is FetchResult.Success &&
                    it.pageNumber == schema.page &&
                    it.generalMovieList == Converter.convertFrom(schema) &&
                    it.maxPageCount == schema.total_pages
        }
    }

    // Find movie error response
    @Test
    fun findMovieAndNotify_validInput_returnFailure() {
        runBlocking {
            // Arrange
            whenever(mMovieRepository.getSearchCount(any(), any())).thenReturn(0)
            failure()
            // Act
            systemUT.findMovie(VALID_PAGE, VALID_QUERY).subscribe(testObserver)
            // Assert
            delay(200)
            testObserver.assertValue {
                it is FetchResult.Failure
            }
        }
    }

    // region helper methods -----------------------------------------------------------------------

    private fun failure() {
        whenever(mFindMovieEndPointMock.findMovieAndNotify(any(), any(), any())).thenAnswer {
            val argument = it.getArgument<Any>(2)
            if (argument is FindMovieEndPoint.Listener) {
                argument.onFetchFailed()
            }
        }
    }

    private fun success(schema: TopRatedMovieSchema) {
        whenever(mFindMovieEndPointMock.findMovieAndNotify(any(), any(), any())).thenAnswer {
            val argument = it.getArgument<Any>(2)
            if (argument is FindMovieEndPoint.Listener) {
                argument.onFetchSuccess(schema)
            }
        }
    }
    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}
