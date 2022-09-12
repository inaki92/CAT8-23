package com.example.yugiohmvvmcat23.utils

import com.example.yugiohmvvmcat23.model.Data

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val cards: List<Data>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
