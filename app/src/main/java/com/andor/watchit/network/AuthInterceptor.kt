package com.andor.watchit.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // DONT INCLUDE API KEYS IN YOUR SOURCE CODE
        val url =
            req.url().newBuilder().addQueryParameter("api_key", "1b86aca7dce418eb4ff10493f30fef44")
                .build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}