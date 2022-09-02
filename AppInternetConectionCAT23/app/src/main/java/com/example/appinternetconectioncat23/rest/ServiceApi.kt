package com.example.appinternetconectioncat23.rest

import com.example.appinternetconectioncat23.model.Flower
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {

    /**
     * Retrofit approach with callback and enqueue
     */
    @GET("feeds/flowers.json")
    fun getFlowers(): Call<List<Flower>>

    /**
     * RxJava approach with [Single]
     */
    @GET("feeds/flowers.json")
    fun getFlowersRx(): Single<List<Flower>>

    companion object {
        const val BASE_URL =
            "https://services.hanselandpetal.com/"
    }
}