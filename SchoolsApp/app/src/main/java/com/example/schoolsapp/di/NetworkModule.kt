package com.example.schoolsapp.di

import com.example.schoolsapp.rest.SchoolsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    /**
     * You can have named dependencies when you need more than one object of the same type
     */
//    @Provides
//    @Named("second")
//    fun providesOkHttpClientS(
//        httpLoggingInterceptor: HttpLoggingInterceptor
//    ): OkHttpClient =
//        OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .build()

    @Provides
    fun providesSchoolApi(okHttpClient: OkHttpClient): SchoolsApi =
        Retrofit.Builder()
            .baseUrl(SchoolsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SchoolsApi::class.java)

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}