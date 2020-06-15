package com.andor.watchit.usecase.tv

import com.andor.watchit.helper.TestData
import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TvSchema
import com.andor.watchit.network.tv.PopularTvEndPoint
import com.andor.watchit.usecase.common.model.TvUiModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import okhttp3.internal.EMPTY_RESPONSE
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PopularTvUseCaseImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------
    // region helper fields ------------------------------------------------------------------------
    @Mock
    private lateinit var mPopularTvEndPointMock: PopularTvEndPoint
    private val testObserver = TestObserver<List<TvUiModel>>()
    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: PopularTvUseCase

    @Before
    fun setup() {
        systemUT = PopularTvUseCaseImpl(mPopularTvEndPointMock)
        // initial state setup
    }

    @Test
    fun getPopularTv_withCorrectData() {
        // Arrange
        success()
        // Act
        systemUT.getPopularTv(10).subscribe(testObserver)
        // Assert
        verify(mPopularTvEndPointMock).getPopularMovies(10)
    }

    @Test
    fun getPopularTv_success_returnGeneralTv() {
        // Arrange
        success()
        // Act
        systemUT.getPopularTv(1).subscribe(testObserver)
        // Assert
        testObserver.assertValueCount(1)
        testObserver.assertValue {
            it == Converter.convertFrom(TestData.TV_SERVER_RESPONSE_POPULAR_TV_SCHEMA)
        }
    }

    @Test
    fun getPopularTv_failure() {
        // Arrange
        failure()
        // Act
        systemUT.getPopularTv(1).subscribe(testObserver)
        // Assert
        Assert.assertEquals(1, testObserver.errorCount())
    }

    // region helper methods -----------------------------------------------------------------------
    private fun success() {
        val successSingle: Single<TvSchema> =
            Single.just(TestData.TV_SERVER_RESPONSE_POPULAR_TV_SCHEMA)
        whenever(mPopularTvEndPointMock.getPopularMovies(any())).thenReturn(successSingle)
    }

    private fun failure() {
        val successSingle: Single<TvSchema> =
            Single.error(HttpException(Response.error<String>(400, EMPTY_RESPONSE)))
        whenever(mPopularTvEndPointMock.getPopularMovies(any())).thenReturn(successSingle)
    }
    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}
