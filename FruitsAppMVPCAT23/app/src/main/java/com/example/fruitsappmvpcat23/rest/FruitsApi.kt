package com.example.fruitsappmvpcat23.rest

import com.example.fruitsappmvpcat23.model.FruitsNetworkItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FruitsApi {

    @GET(ALL_PATH)
    fun getAllFruits(): Single<List<FruitsNetworkItem>>

    @GET(SEARCH_PATH)
    fun searchFruit(
        @Path("fruit") fruitName: String
    ): Single<FruitsNetworkItem>

    companion object {
        const val BASE_URL = "https://www.fruityvice.com/api/fruit/"
        private const val SEARCH_PATH = "{fruit}"
        private const val ALL_PATH = "all"
    }

}