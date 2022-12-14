package com.example.spotifyapp.rest

import com.example.spotifyapp.model.authentication.TokenHandling
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    private val tokenHandling: TokenHandling
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().apply {
            // todo fix issues with token HEADER
            if(chain.request().method == "POST") {
                addHeader(AUTH_HEADER, "Basic ${tokenHandling.authorizationKey}")
            } else {
                addHeader(AUTH_HEADER, "Bearer ${tokenHandling.authToken}")
            }
        }.also {
            return chain.proceed(it.build())
        }
    }

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }
}