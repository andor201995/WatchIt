package com.andor.watchit.helper

import com.andor.watchit.network.schema.TopRatedMovieSchema
import com.google.gson.Gson
import java.io.File

object TestData {

    val SERVER_RESPONSE_JSON_SUCCESS_NULL: String = getJson("nullData.json")
    val SERVER_RESPONSE_JSON_SUCCESS: String = getJson("success.json")

    val SERVER_RESPONSE_TOP_RATED_MOVIE_SCHEMA: TopRatedMovieSchema =
        Gson().fromJson<TopRatedMovieSchema>(
            SERVER_RESPONSE_JSON_SUCCESS,
            TopRatedMovieSchema::class.java
        )

    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */
    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}
