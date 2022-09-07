package com.example.flowersmvpcat23.model.domain

import com.example.flowersmvpcat23.model.FlowersItem

data class FlowerDomain(
    val flowerName: String,
    val flowerCategory: String,
    val flowerPrice: Double,
    val flowerPhoto: String
)

fun List<FlowersItem>.mapToFlowerDomain(): List<FlowerDomain> {
    return this.map {
        FlowerDomain(
            it.name ?: "",
            it.category ?: "",
            it.price ?: 0.0,
            it.photo ?: ""
        )
    }
}
