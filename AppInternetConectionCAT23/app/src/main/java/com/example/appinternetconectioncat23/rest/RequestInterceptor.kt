package com.example.appinternetconectioncat23.rest

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * This is an interceptor to add headers to the request
 *
 * 1. Create a class for the interceptor
 * 2. Implementing the [Interceptor] interface from OKHTTP library
 * 3. You override the intercept method
 * 4. You get the request and a new builder to add the headers from the chain
 * 5. Add the headers that are KEY-VALUE pairs
 * 6. Call chain.proceed to create a response and return it
 */
class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().apply {
            addHeader("authorization", "myToken")
            addHeader("environment", "QA")
            addHeader("version", "1.0")
        }.build().also {
            return chain.proceed(it)
        }
    }
}

/**
 * This is an interceptor to check the response back from the server
 *
 * 1. Create a class for the interceptor
 * 2. Implementing the [Interceptor] interface from OKHTTP library
 * 3. You override the intercept method
 * 4. You get the request
 * 5. Call chain.proceed to get the response
 * 6. Check for the HTTP code
 * 7. Return the [Response]
 */
class ResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().also {
            with(chain.proceed(it)) {
                if (this.code == 401) {
                    Log.d(TAG, "intercept: Refresh auth token")
                }

                return this
            }
        }
    }

    companion object {
        private const val TAG = "RequestInterceptor"
    }

}