package com.example.acronymappcompose.rest

import com.example.acronymappcompose.model.AbbreviationItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymApi {

    @GET(SEARCH_PATH)
    suspend fun getMeaning(
        @Query("sf") acronym: String
    ): Response<List<AbbreviationItem>>

    companion object {
        // http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM
        const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"
        private const val SEARCH_PATH = "dictionary.py"
    }
}