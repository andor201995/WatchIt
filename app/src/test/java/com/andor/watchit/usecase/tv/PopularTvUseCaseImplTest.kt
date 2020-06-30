package com.andor.watchit.usecase.tv

import com.andor.watchit.helper.TestData.TV_SERVER_RESPONSE_POPULAR_TV_SCHEMA
import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TvSchema
import com.andor.watchit.network.endpoints.tv.PopularTvEndPoint
import com.andor.watchit.repository.tv.TvRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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
    private val testObserver = TestObserver<PopularTvUseCase.FetchResult>()

    @Mock
    private lateinit var tvRepository: TvRepository
    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: PopularTvUseCase

    @Before
    fun setup() {
        systemUT = PopularTvUseCaseImpl(mPopularTvEndPointMock, tvRepository)
        // initial state setup
    }

    @Test
    fun getPopularTv_withCorrectData() {
        runBlocking {
            // Arrange
            val schema = TV_SERVER_RESPONSE_POPULAR_TV_SCHEMA
            success(schema)
            // Act
            systemUT.getPopularTv(10).subscribe(testObserver)
            // Assert
            delay(200)
            verify(mPopularTvEndPointMock).getPopularMovies(10)
        }
    }

    @Test
    fun getPopularTv_success_returnGeneralTv() {
        runBlocking {
            // Arrange
            val schema = TV_SERVER_RESPONSE_POPULAR_TV_SCHEMA
            success(schema)
            // Act
            systemUT.getPopularTv(1).subscribe(testObserver)
            // Assert
            delay(200)
            testObserver.assertValueCount(1)
            testObserver.assertValue {
                it == PopularTvUseCase.FetchResult.Success(
                    Converter.convertFrom(schema),
                    schema.page,
                    schema.total_pages,
                    schema.total_results
                )
            }
        }
    }

    @Test
    fun getPopularTv_failure() {
        runBlocking {
            // Arrange
            failure()
            // Act
            systemUT.getPopularTv(1).subscribe(testObserver)
            // Assert
            delay(200)
            Assert.assertEquals(1, testObserver.valueCount())
            testObserver.assertValue {
                it == PopularTvUseCase.FetchResult.Failure
            }
        }
    }

    // region helper methods -----------------------------------------------------------------------
    private suspend fun success(schema: TvSchema) {
        val successSingle: Single<TvSchema> =
            Single.just(TV_SERVER_RESPONSE_POPULAR_TV_SCHEMA)

        whenever(tvRepository.getTvCount()).thenReturn(schema.total_results)
        whenever(tvRepository.getPagedTv(any())).thenReturn(Converter.convertFrom(schema))

        whenever(mPopularTvEndPointMock.getPopularMovies(any())).thenReturn(successSingle)
    }

    private suspend fun failure() {
        whenever(tvRepository.getTvCount()).thenReturn(0)

        val failureSingle: Single<TvSchema> =
            Single.error(HttpException(Response.error<String>(400, EMPTY_RESPONSE)))
        whenever(mPopularTvEndPointMock.getPopularMovies(any())).thenReturn(failureSingle)
    }
    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}
