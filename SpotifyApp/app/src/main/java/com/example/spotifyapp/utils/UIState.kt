package com.example.spotifyapp.utils

import com.example.spotifyapp.model.domain.DomainArtist

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val data: List<DomainArtist>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
