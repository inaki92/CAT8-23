package com.example.acronymappcompose.rest

import android.content.Context
import com.example.acronymappcompose.model.domain.mapToDomainMeaning
import com.example.acronymappcompose.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface AcronymRepository {
    fun getMeaning(acronym: String): Flow<UIState>
}

class AcronymRepositoryImpl(
    private val apiService: AcronymApi,
    private val ioDispatcher: CoroutineDispatcher
) : AcronymRepository {

    override fun getMeaning(acronym: String): Flow<UIState> = flow {
        emit(UIState.LOADING)

        val response = apiService.getMeaning(acronym)
        if(response.isSuccessful) {
            response.body()?.let {
                emit(UIState.SUCCESS(it.mapToDomainMeaning()))
            } ?: throw Exception("Response body is null")
        } else {
            throw Exception("Failure response")
        }
    }.flowOn(ioDispatcher)
}