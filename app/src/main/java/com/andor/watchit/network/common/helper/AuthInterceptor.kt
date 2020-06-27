package com.andor.watchit.network.common.helper

import com.andor.watchit.core.Constants.AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        // DONT INCLUDE API KEYS IN YOUR SOURCE CODE
        val url =
            req.url().newBuilder().addQueryParameter("api_key", AUTH_TOKEN)
                .build()
        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
