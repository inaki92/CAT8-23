package com.example.yugiohmvvmcat23.model.domain

import com.example.yugiohmvvmcat23.model.Data

data class DomainCard(
    val cardName: String,
    val cardType: String,
    val cardImage: String? = null,
    val cardMarketPrice: String,
    val cardRace: String,
    val cardDescription: String
)

fun List<Data?>?.mapToDomainCards(): List<DomainCard> {
    return this?.map { card ->
        DomainCard(
            cardName = card?.name ?: "Invalid Name",
            cardType = card?.type ?: "Invalid Type",
            cardDescription = card?.desc ?: "No Description",
            cardRace = card?.race ?: "Invalid Race",
            cardMarketPrice = card?.cardPrices?.firstOrNull()?.cardmarketPrice ?: "N/A",
            cardImage = card?.cardImages?.firstOrNull()?.imageUrl
        )
    } ?: emptyList()
}
