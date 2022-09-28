package com.example.countriesapp.utils

import com.example.countriesapp.model.domain.DomainCountry

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val data: List<DomainCountry>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
