package com.andor.watchit.network.common.helper

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ConverterTest {
    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------

    // region helper fields ------------------------------------------------------------------------
    // end region helper fields --------------------------------------------------------------------

    private lateinit var systemUT: Converter

    @Before
    fun setup() {
        systemUT = Converter
        // initial state setup
    }

    @Test
    fun convertFrom_singleSpaceQuery_returnTransformedQuery() {
        val actualQuery = systemUT.convertFrom("TEST QUERY CHECK")
        Assert.assertEquals("TEST+QUERY+CHECK", actualQuery)
    }

    @Test
    fun convertFrom_multipleSpaceQuery_returnTransformedQuery() {
        val actualQuery = systemUT.convertFrom("        TEST           QUERY   CHECK      ")
        Assert.assertEquals("TEST+QUERY+CHECK", actualQuery)
    }

    // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------

    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}
