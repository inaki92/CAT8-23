package com.example.fruitsappmvvm.rest

import com.example.fruitsappmvvm.model.Fruit
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface FruitsApi {

    @GET(ALL_PATH)
    fun getFruitsAsync(): Deferred<Response<List<Fruit>>>

    @PUT(API_PATH)
    suspend fun postNewFruit(
        @Body fruit: Fruit
    ): Response<Unit>


    companion object {
        const val BASE_URL = "https://www.fruityvice.com/"
        private const val API_PATH = "api/fruit/"
        private const val SEARCH_PATH = "$API_PATH{fruit}"
        private const val ALL_PATH = "${API_PATH}all"
    }
    //@GET(ALL_PATH)
    //    fun getAllFruits(): Single<List<FruitsNetworkItem>>
    //
    //    @GET(SEARCH_PATH)
    //    fun searchFruit(
    //        @Path("fruit") fruitName: String
    //    ): Single<FruitsNetworkItem>
    //
    //
}