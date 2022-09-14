package com.example.musicexampleapp.rest

import androidx.lifecycle.LiveData
import com.example.musicexampleapp.model.Song
import com.example.musicexampleapp.model.Songs
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {

    @GET(PATH_SEARCH)
    fun getMusic(
        @Query("term") genre: String = "pop",
        @Query("amp;media") media: String = "music",
        @Query("amp;entity") entity: String = "song",
        @Query("amp;limit") limit: Int = 50,
    ): Single<Songs>

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        private const val PATH_SEARCH = "search"

        private val okhttp =
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()

        val serviceApi: MusicApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okhttp)
                .build()
                .create(MusicApi::class.java)
    }
}