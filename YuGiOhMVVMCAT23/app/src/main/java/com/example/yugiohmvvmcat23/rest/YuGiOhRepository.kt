package com.example.yugiohmvvmcat23.rest

import com.example.yugiohmvvmcat23.utils.CardType
import com.example.yugiohmvvmcat23.utils.FailureResponseFromServer
import com.example.yugiohmvvmcat23.utils.NullResponseFromServer
import com.example.yugiohmvvmcat23.utils.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface YuGiOhRepository {
    fun getCardByType(cardType: CardType): Flow<UIState>
}

class YuGiOhRepositoryImpl(
    private val serviceApi: YuGiOhApi
) : YuGiOhRepository {

    override fun getCardByType(cardType: CardType): Flow<UIState> = flow {
        emit(UIState.LOADING)

        // Do not add delays on production code
        delay(2000)

        try {
            val response = serviceApi.getCardsByType(cardType.typeName)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.cards))
                } ?: throw NullResponseFromServer("Cards are null")
            } else {
                throw FailureResponseFromServer(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }
}