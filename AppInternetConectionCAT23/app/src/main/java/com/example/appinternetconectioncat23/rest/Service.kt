package com.example.appinternetconectioncat23.rest

import com.example.appinternetconectioncat23.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This is a singleton object created in kotlin
 */
object Service {

    /**
     * This is the logging interceptor that will log the request in the logcat
     *
     * 1. Create the [HttpLoggingInterceptor] instance
     * 2. Apply the [HttpLoggingInterceptor.Level] required for logging
     */
    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    /**
     * This is the http client object, that allows you to add headers and extra information to the request
     *
     * 1. Create the Builder from [OkHttpClient]
     * 2. Add the required interceptors
     * 3. Add the Logging interceptor at the end
     * 4. Add the timeouts for the connection
     * 5. Build the object
     */
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addInterceptor(ResponseInterceptor())
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * This is the interface creator for you Service API that retrofit will use
     *
     * 1. Create the Builder from [Retrofit]
     * 2. Add the BASE URL
     * 3. Add the converter factory
     * 4. Add the Call adapter factory (if needed using RXJAVA)
     * 5. Add you OKHTTP object
     * 6. Build and create the interface passing the reference of it
     */
    val retrofitService: ServiceApi by lazy {
        Retrofit.Builder()
            .baseUrl(ServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ServiceApi::class.java)
    }
}