package com.example.spotifyapp.model.domain

import com.example.spotifyapp.model.Artist
import com.example.spotifyapp.model.Image

data class DomainArtist(
    val id: String,
    val name: String,
    val popularity: Int,
    val images: List<Image>,
    val deepLink: String,
    val genres: List<String>
)

fun List<Artist>?.mapToDomainArtist(): List<DomainArtist> =
    this?.map {
        DomainArtist(
            id = it.id ?: "",
            name = it.name ?: "",
            popularity = it.popularity ?: 0,
            images = it.images ?: emptyList(),
            deepLink = it.uri ?: it.externalUrls?.spotify ?: "",
            genres = it.genres ?: emptyList()
        )
    } ?: emptyList()