package com.example.graphqlspaceexample.utils

import com.example.graphqlspaceexample.model.DomainLaunch

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val data: List<DomainLaunch>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
