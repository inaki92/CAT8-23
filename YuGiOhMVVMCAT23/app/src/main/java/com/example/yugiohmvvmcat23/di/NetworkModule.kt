package com.example.yugiohmvvmcat23.di

import com.example.yugiohmvvmcat23.rest.RequestInterceptor
import com.example.yugiohmvvmcat23.rest.ResponseInterceptor
import com.example.yugiohmvvmcat23.rest.YuGiOhApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesRequestInterceptor(): RequestInterceptor =
        RequestInterceptor()

    @Provides
    fun providesResponseInterceptor(): ResponseInterceptor =
        ResponseInterceptor()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesOkHttpClient(
        requestInterceptor: RequestInterceptor,
        responseInterceptor: ResponseInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(responseInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(YuGiOhApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun providesYuGiOhApi(retrofit: Retrofit): YuGiOhApi =
        retrofit.create(YuGiOhApi::class.java)
}