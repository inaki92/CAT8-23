package com.example.acronymappcompose.rest

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val cacheControl = CacheControl.Builder()
            .maxAge(5, TimeUnit.MINUTES)
            .build()

        return response.newBuilder()
            .header("MY_CACHE", cacheControl.toString())
            .build()
    }
}