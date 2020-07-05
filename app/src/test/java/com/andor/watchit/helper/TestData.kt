package com.andor.watchit.helper

import com.andor.watchit.network.common.schema.MovieSchema
import com.andor.watchit.network.common.schema.TvSchema
import com.google.gson.Gson
import java.io.File

object TestData {

    val TV_SERVER_RESPONSE_JSON_SUCCESS: String = getJson("success_tv.json")
    val SERVER_RESPONSE_JSON_SUCCESS_NULL: String = getJson("nullData.json")
    val SERVER_RESPONSE_JSON_SUCCESS: String = getJson("success.json")

    val SERVER_RESPONSE_MOVIE_SCHEMA: MovieSchema =
        Gson().fromJson<MovieSchema>(
            SERVER_RESPONSE_JSON_SUCCESS,
            MovieSchema::class.java
        )

    val TV_SERVER_RESPONSE_POPULAR_TV_SCHEMA: TvSchema =
        Gson().fromJson(
            TV_SERVER_RESPONSE_JSON_SUCCESS,
            TvSchema::class.java
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
