package com.example.spotifyapp.rest

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().apply {
            addHeader(AUTH_HEADER, "Basic ZjY5YzE0YWFiMzMwNDhhZGFiZThmZWJkM2IyYjc1ZTg6ODQ4OWIzODM1YzE5NGQwNzg1YjdiY2M5ZDI1YzRlYjk=")
        }.also {
            return chain.proceed(it.build())
        }
    }

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }
}