package com.example.appinternetconectioncat23.rest

import com.example.appinternetconectioncat23.model.Flower
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {

    @GET("feeds/flowers.json")
    fun getFlowers(): Call<List<Flower>>

    companion object {
        const val BASE_URL =
            "https://services.hanselandpetal.com/"
    }
}