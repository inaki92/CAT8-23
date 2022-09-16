package com.example.schoolsapp.utils

import com.example.schoolsapp.model.domain.School

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS<out T>(val data: T) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
