package com.example.acronymappcompose.utils

import com.example.acronymappcompose.model.domain.DomainMeaning

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val data: List<DomainMeaning>) : UIState()
    data class ERROR(val error: Throwable) : UIState()
}