package com.example.yugiohmvvmcat23.rest

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

private const val TAG = "Interceptors"

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request().newBuilder().apply {
                addHeader("TOKEN", "THis is your oAuthToken")
                addHeader("Env", "QA")
                addHeader("version", "1.0")
            }.build()
        )
}

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request()).apply {
            if (this.code == 200) {
                Log.d(TAG, "intercept: SUCCESS RESPONSE")
            } else {
                Log.e(TAG, "intercept: Failure Response", )
            }
        }
    }
}