package com.example.flowersmvpcat23.rest

import com.example.flowersmvpcat23.model.FlowersItem
import io.reactivex.Single
import retrofit2.http.GET

interface FlowersAPI {

    /**
     * This method will talk to the server to retrieve flowers from the JSON
     *
     * @return [Single] - This is an observable from RXJAVA that allows you to have
     * multithreading in Android and do the operation asynchronously
     * without blocking the main thread
     *
     * This guy will return a single object either SUCCESS or ERROR
     */
    @GET("feeds/flowers.json")
    fun getAllFlowers(): Single<List<FlowersItem>>

    companion object {
        const val BASE_URL = "https://services.hanselandpetal.com/"
        const val IMAGE_PATH = "${BASE_URL}photos/"
    }
}