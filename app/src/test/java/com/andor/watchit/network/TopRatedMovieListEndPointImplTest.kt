package com.andor.watchit.network

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopRatedMovieListEndPointImplTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    @Mock
    lateinit var listener: TopRatedMovieListEndPoint.Listener
    @Mock
    lateinit var movieApi: MovieApi
    // end region helper fields --------------------------------------------------------------------

    internal lateinit var systemUT: TopRatedMovieListEndPoint

    @Before
    fun setup() {
        systemUT = TopRatedMovieListEndPointImpl(movieApi)
        // initial state setup
    }

    // fetch success with correct data
    @Test
    fun onFetchTopRatedMovieListAndNotify_fetchSuccess_validData() {
        //Arrange
        //Act
        systemUT.onFetchTopRatedMovieListAndNotify(listener)
        //Assert
        verify(listener, times(1)).onFetchSuccess(any())
    }


    // fetch failure with no data and error msg

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}