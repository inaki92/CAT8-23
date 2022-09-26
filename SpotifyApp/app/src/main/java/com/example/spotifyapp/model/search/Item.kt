package com.example.spotifyapp.model.search


import com.example.spotifyapp.model.Artist
import com.example.spotifyapp.model.ExternalUrls
import com.example.spotifyapp.model.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "album_type")
    val albumType: String? = "",
    @Json(name = "artists")
    val artists: List<Artist>? = listOf(),
    @Json(name = "available_markets")
    val availableMarkets: List<String>? = listOf(),
    @Json(name = "external_urls")
    val externalUrls: ExternalUrls? = ExternalUrls(),
    @Json(name = "href")
    val href: String? = "",
    @Json(name = "id")
    val id: String? = "",
    @Json(name = "images")
    val images: List<Image>? = listOf(),
    @Json(name = "name")
    val name: String? = "",
    @Json(name = "release_date")
    val releaseDate: String? = "",
    @Json(name = "release_date_precision")
    val releaseDatePrecision: String? = "",
    @Json(name = "total_tracks")
    val totalTracks: Int? = 0,
    @Json(name = "type")
    val type: String? = "",
    @Json(name = "uri")
    val uri: String? = ""
)