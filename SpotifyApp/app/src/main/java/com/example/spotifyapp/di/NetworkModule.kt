package com.example.spotifyapp.di

import com.example.spotifyapp.model.authentication.TokenHandling
import com.example.spotifyapp.rest.RequestInterceptor
import com.example.spotifyapp.rest.ResponseInterceptor
import com.example.spotifyapp.rest.SpotifyServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun providesRequestInterceptor(tokenHandling: TokenHandling): RequestInterceptor =
        RequestInterceptor(tokenHandling)

    @Provides
    fun providesResponseInterceptor(): ResponseInterceptor =
        ResponseInterceptor()

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: RequestInterceptor,
        responseInterceptor: ResponseInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(responseInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Named("authService")
    fun providesAuthenticationService(
        okHttpClient: OkHttpClient
    ): SpotifyServiceApi =
        Retrofit.Builder()
            .baseUrl(SpotifyServiceApi.AUTH_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SpotifyServiceApi::class.java)

    @Provides
    @Named("spotifyService")
    fun providesSpotifyServiceApi(
        okHttpClient: OkHttpClient
    ): SpotifyServiceApi =
        Retrofit.Builder()
            .baseUrl(SpotifyServiceApi.SERVICE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SpotifyServiceApi::class.java)
}