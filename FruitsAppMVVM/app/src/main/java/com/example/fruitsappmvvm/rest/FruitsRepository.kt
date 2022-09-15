package com.example.fruitsappmvvm.rest

import com.example.fruitsappmvvm.model.Fruit
import com.example.fruitsappmvvm.utils.FailureResponseException
import com.example.fruitsappmvvm.utils.NullResponseException
import com.example.fruitsappmvvm.utils.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface FruitsRepository {
    fun getFruits(): Flow<UIState>
    fun createFruit(fruit: Fruit): Flow<UIState>
}

class FruitsRepositoryImpl @Inject constructor(
    private val fruitsApi: FruitsApi
) : FruitsRepository {

    override fun getFruits(): Flow<UIState> =
        flow {
            emit(UIState.LOADING)

            delay(2000)

            try {
                val response = fruitsApi.getFruitsAsync().await()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(UIState.SUCCESS(it))
                    } ?: throw NullResponseException("fruits are null")
                } else {
                    throw FailureResponseException(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                emit(UIState.FAILURE(e))
            }
        }

    override fun createFruit(fruit: Fruit): Flow<UIState> =
        flow {
            emit(UIState.LOADING)

            delay(2000)

            try {
                val response = fruitsApi.postNewFruit(fruit)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(UIState.UPDATED)
                    } ?: throw NullResponseException("Error body null when updating fruit")
                } else {
                    throw FailureResponseException(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                emit(UIState.FAILURE(e))
            }
        }

}