package com.andor.watchit.screens.topratedmovielist

import com.andor.watchit.core.Convertor
import com.andor.watchit.helper.TestData
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCaseImpl
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopRatedMovieListViewModelTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    @Mock
    private lateinit var mTopRatedMovieUseCaseMock: TopRatedMovieUseCase
    private val testObserver = TestObserver<TopRatedMovieScreenState>()
    private val fetchStream: PublishSubject<TopRatedMovieUseCaseImpl.FetchResult> =
        PublishSubject.create()

    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: TopRatedMovieListViewModel

    @Before
    fun setup() {
        whenever(mTopRatedMovieUseCaseMock.getResultStream()).thenReturn(fetchStream)
        systemUT = TopRatedMovieListViewModel(mTopRatedMovieUseCaseMock)
        // initial state setup
    }

    @After
    fun tearDown() {
        testObserver.dispose()
    }

    @Test
    fun fetchTopRatedMovieAndNotify_fetchSuccess_returnValidData() {
        //Arrange
        systemUT.screenStateStream.subscribe(testObserver)
        //Act
        systemUT.fetchTopRatedMovieAndNotify()
        success()
        //Assert
        verify(mTopRatedMovieUseCaseMock).fetchTopRatedMovieAndNotify()

        testObserver.assertValueSequence(
            listOf(
                TopRatedMovieScreenState(listOf(), ScreenStatus.LOADING),
                TopRatedMovieScreenState(
                    Convertor.convertFrom(TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA),
                    ScreenStatus.FETCH_SUCCESS
                )
            )
        )
    }

    @Test
    fun fetchTopRatedMovieAndNotify_fetchFail_returnFailStatus() {
        //Arrange
        systemUT.screenStateStream.subscribe(testObserver)
        //Act
        systemUT.fetchTopRatedMovieAndNotify()
        failure()
        //Assert
        verify(mTopRatedMovieUseCaseMock).fetchTopRatedMovieAndNotify()

        testObserver.assertValueSequence(
            listOf(
                TopRatedMovieScreenState(listOf(), ScreenStatus.LOADING),
                TopRatedMovieScreenState(listOf(), ScreenStatus.FETCH_FAILED)
            )
        )

    }

    // region helper methods -----------------------------------------------------------------------

    private fun success() {
        fetchStream.onNext(
            TopRatedMovieUseCaseImpl.FetchResult.Success(
                Convertor.convertFrom(
                    TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA
                )
            )
        )
    }

    private fun failure() {
        fetchStream.onNext(
            TopRatedMovieUseCaseImpl.FetchResult.Failure
        )
    }

    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}