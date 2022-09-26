package com.example.spotifyapp.utils

sealed class PopularityRates {
    data class POPULARITY(val popularity: Int): PopularityRates()
    object NO_POPULARITY: PopularityRates()
}