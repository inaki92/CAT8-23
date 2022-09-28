package com.example.graphqlspaceexample.network

import com.apollographql.apollo3.ApolloClient
import com.example.graphqlspaceexample.LaunchListQuery
import com.example.graphqlspaceexample.model.mapToDomainLaunch
import com.example.graphqlspaceexample.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SpaceRepository {
    suspend fun getLaunchedRockets(): Flow<UIState>
}

class SpaceRepositoryImpl(
    private val serviceApi: ApolloClient
) : SpaceRepository {
    override suspend fun getLaunchedRockets(): Flow<UIState> = flow {
        emit(UIState.LOADING)

        try {
            val response = serviceApi.query(LaunchListQuery(10)).execute()
            if (!response.hasErrors()) {
                response.data?.let {
                    it.launchesPast?.let { data ->
                        emit(UIState.SUCCESS(data.mapToDomainLaunch()))
                    } ?: throw Exception("Data is null")
                } ?: throw Exception("Response data is null")
            } else {
                throw Exception(response.errors.toString())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}