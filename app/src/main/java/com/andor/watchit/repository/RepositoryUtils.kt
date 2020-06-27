package com.andor.watchit.repository

object RepositoryUtils {

    fun constructSearchQuery(
        query: List<String>,
        tableName: String,
        conditionalColumn: String
    ): StringBuilder {
        val queryString =
            StringBuilder("SELECT * FROM $tableName")

        if (query.isNotEmpty()) {
            queryString.append("\nWHERE $conditionalColumn like '%${query[0]}%'")
        }

        if (query.size > 1) {
            (1 until query.size).forEach { index ->
                queryString.append("\nOR $conditionalColumn like '%${query[index]}%' ")
            }
        }
        return queryString
    }
}
