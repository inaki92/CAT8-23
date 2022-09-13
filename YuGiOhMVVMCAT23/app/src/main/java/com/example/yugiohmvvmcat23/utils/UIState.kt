package com.example.yugiohmvvmcat23.utils

import com.example.yugiohmvvmcat23.model.Data
import com.example.yugiohmvvmcat23.model.domain.DomainCard

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val cards: List<DomainCard>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
