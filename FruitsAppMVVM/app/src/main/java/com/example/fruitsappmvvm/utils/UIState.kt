package com.example.fruitsappmvvm.utils

import com.example.fruitsappmvvm.model.Fruit

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val fruits: List<Fruit>) : UIState()
    data class FAILURE(val error: Exception) : UIState()
    object UPDATED : UIState()

}
