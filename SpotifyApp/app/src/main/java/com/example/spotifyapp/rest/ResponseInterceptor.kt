package com.example.spotifyapp.rest

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor : Interceptor {

    private val _unauthorizedError: MutableStateFlow<Int?> = MutableStateFlow(null)
    val unauthorizedError: StateFlow<Int?> get() = _unauthorizedError

    private var counter: Int = 0

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        response.request.url
        if (response.code == 401) {
            _unauthorizedError.value = counter
            counter++
        }
        return response
    }
}