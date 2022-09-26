package com.example.spotifyapp.utils

import com.example.spotifyapp.model.domain.DomainArtist

sealed class ItemView {
    data class HeaderName(val artistName: String) : ItemView()
    data class ArtistItem(val artist: DomainArtist) : ItemView()
}
