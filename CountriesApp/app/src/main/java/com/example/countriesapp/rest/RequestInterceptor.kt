package com.example.countriesapp.rest

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().apply {
            addHeader("AUTH", "This is the token")
            addHeader("ENV", "PROD")
        }.build()
            .also { return chain.proceed(it) }
    }
}