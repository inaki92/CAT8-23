package com.example.acronymappcompose.di

import android.content.Context
import com.example.acronymappcompose.rest.AcronymApi
import com.example.acronymappcompose.rest.CacheInterceptor
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val cacheSize = 10 * 1024 * 1024 // 10MB

@Module
class NetworkModule {

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesCacheFile(context: Context): Cache =
        Cache(context.cacheDir, cacheSize.toLong())

    @Provides
    fun providesCacheInterceptor(): CacheInterceptor = CacheInterceptor()

    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cacheInterceptor: CacheInterceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(cacheInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesAcronymService(
        okHttpClient: OkHttpClient
    ): AcronymApi =
        Retrofit.Builder()
            .baseUrl(AcronymApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AcronymApi::class.java)

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher =
        Dispatchers.IO
}