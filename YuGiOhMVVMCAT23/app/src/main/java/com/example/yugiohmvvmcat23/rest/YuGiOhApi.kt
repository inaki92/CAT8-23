package com.example.yugiohmvvmcat23.rest

import com.example.yugiohmvvmcat23.model.Card
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YuGiOhApi {

    /**
     * This method is calling this endpoint
     *
     * https://db.ygoprodeck.com/api/v7/cardinfo.php?type=Spell Card
     */
    @GET(GENERAL_PATH)
    suspend fun getCards(
        @Query("type") cardType: String? = null,
        @Query("fname") searchName: String? = null
    ): Response<Card>

    companion object {
        const val BASE_URL = "https://db.ygoprodeck.com/api/v7/"
        private const val GENERAL_PATH = "cardinfo.php"
    }
}