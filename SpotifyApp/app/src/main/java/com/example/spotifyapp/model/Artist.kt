package com.example.spotifyapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Artist(
    @Json(name = "external_urls")
    val externalUrls: ExternalUrls? = null,
    @Json(name = "followers")
    val followers: Followers? = null,
    @Json(name = "genres")
    val genres: List<String>? = null,
    @Json(name = "href")
    val href: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "images")
    val images: List<Image>? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "popularity")
    val popularity: Int? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "uri")
    val uri: String? = null
)