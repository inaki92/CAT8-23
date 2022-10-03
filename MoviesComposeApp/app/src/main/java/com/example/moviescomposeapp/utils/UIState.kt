package com.example.moviescomposeapp.utils

import com.example.moviescomposeapp.model.DomainMovie

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val data: List<DomainMovie>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
