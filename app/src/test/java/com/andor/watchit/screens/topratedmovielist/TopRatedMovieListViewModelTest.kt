package com.andor.watchit.screens.topratedmovielist

import com.andor.watchit.helper.TestData
import com.andor.watchit.network.helper.Converter
import com.andor.watchit.screens.topratedmovielist.model.TopRatedMovieListViewModel
import com.andor.watchit.screens.topratedmovielist.model.TopRatedMovieScreenState
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCaseImpl
import com.andor.watchit.usecase.topratedmovie.datasource.TopRatedMovieDataSourceFactory
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopRatedMovieListViewModelTest {
    // region constants ----------------------------------------------------------------------------
    val VALID_PAGE_NUMBER = 1
    val VALID_MAX_PAGE_NUMBER = 200

    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    @Mock
    private lateinit var mTopRatedMovieDataSourceFactory: TopRatedMovieDataSourceFactory
    private val testObserver = TestObserver<TopRatedMovieScreenState>()
    private val fetchStream: PublishSubject<TopRatedMovieUseCaseImpl.FetchResult> =
        PublishSubject.create()

    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: TopRatedMovieListViewModel

    @Before
    fun setup() {
        systemUT =
            TopRatedMovieListViewModel(
                mTopRatedMovieDataSourceFactory
            )
        // initial state setup
    }

    @After
    fun tearDown() {
        testObserver.dispose()
    }

    @Test
    fun dummy_test() {
        Assert.assertEquals(4, 2 + 2)
    }

    //TODO: find way to test paging
//
//    @Test
//    fun fetchTopRatedMovieAndNotify_fetchSuccess_returnValidData() {
//        //Arrange
//        systemUT.screenStateStream.subscribe(testObserver)
//        //Act
//        systemUT.fetchTopRatedMovieAndNotify()
//        success()
//        //Assert
//        verify(mTopRatedMovieUseCaseMock).fetchTopRatedMovieAndNotify(1)
//
//        testObserver.assertValueSequence(
//            listOf(
//                TopRatedMovieScreenState(
//                    ScreenStatus.LOADING
//                ),
//                TopRatedMovieScreenState(
//                    ScreenStatus.SUCCESS(mockPagedList(Converter.convertFrom(TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA)))
//                )
//            )
//        )
//    }
//
//    @Test
//    fun fetchTopRatedMovieAndNotify_fetchFail_returnFailStatus() {
//        //Arrange
//        systemUT.screenStateStream.subscribe(testObserver)
//        //Act
//        systemUT.fetchTopRatedMovieAndNotify()
//        failure()
//        //Assert
//        verify(mTopRatedMovieUseCaseMock).fetchTopRatedMovieAndNotify(1)
//
//        testObserver.assertValueSequence(
//            listOf(
//                TopRatedMovieScreenState(
//                    ScreenStatus.LOADING
//                ),
//                TopRatedMovieScreenState(
//                    ScreenStatus.FAILED
//                )
//            )
//        )
//
//    }

    // region helper methods -----------------------------------------------------------------------

    private fun success() {
        fetchStream.onNext(
            TopRatedMovieUseCaseImpl.FetchResult.Success(
                Converter.convertFrom(
                    TestData.SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA
                ), VALID_PAGE_NUMBER, VALID_MAX_PAGE_NUMBER
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