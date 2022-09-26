package com.example.spotifyapp.model.domain

import com.example.spotifyapp.model.Artist
import com.example.spotifyapp.model.Image
import com.example.spotifyapp.model.search.Item
import com.example.spotifyapp.utils.PopularityRates

data class DomainArtist(
    val id: String,
    val name: String,
    val popularity: PopularityRates,
    val images: List<Image>,
    val deepLink: String,
    val genres: List<String>,
    val type: String
)

fun List<Artist>?.mapToDomainArtist(): List<DomainArtist> =
    this?.map {
        DomainArtist(
            id = it.id ?: "",
            name = it.name ?: "",
            popularity = if(it.popularity == null)
                PopularityRates.NO_POPULARITY else PopularityRates.POPULARITY(it.popularity),
            images = it.images ?: emptyList(),
            deepLink = it.uri ?: it.externalUrls?.spotify ?: "",
            genres = it.genres ?: emptyList(),
            type = it.type ?: ""
        )
    } ?: emptyList()

fun List<Item>?.mapToDomainArtistSearch(): List<DomainArtist> =
    this?.flatMap {
        it.artists.mapToDomainArtist()
    } ?: emptyList()