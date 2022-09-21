package com.example.disneycodechallenge_generic.utils

import com.example.disneycodechallenge_generic.model.domain.DomainUser

sealed class ResultState {
    object LOADING : ResultState()
    data class SUCCESS(val data: List<DomainUser>) : ResultState()
    data class ERROR(val error: Exception) : ResultState()
}
