package com.example.acronymappcompose.rest

import com.example.acronymappcompose.model.AbbreviationItem
import retrofit2.Response
import javax.inject.Inject

interface AcronymRepository {
    suspend fun getMeaning(acronym: String): Response<List<AbbreviationItem>>
}

class AcronymRepositoryImpl @Inject constructor(
    private val apiService: AcronymApi
) : AcronymRepository {

    override suspend fun getMeaning(acronym: String): Response<List<AbbreviationItem>> =
        apiService.getMeaning(acronym)
}