package com.example.fruitsappmvpcat23.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fruitsappmvpcat23.model.FruitsNetworkItem
import com.example.fruitsappmvpcat23.model.Nutritions

/**
 * When working with the ROOM DB you just need to use the entity annotation
 * and provide the table name and the primary key
 */
@Entity
data class FruitDomain(
    @PrimaryKey val fruitName: String,
    // val nutritions: Nutritions,
    val family: String,
    val genus: String
)

fun List<FruitsNetworkItem>
        .mapToDomainFruitList(): List<FruitDomain> =
    this.map {
        FruitDomain(
            fruitName = it.name ?: "",
            // nutritions = it.nutritions ?: Nutritions(),
            family = it.family ?: "",
            genus = it.genus ?: ""
        )
    }

fun FruitsNetworkItem.mapToDomainFruit(): FruitDomain =
    FruitDomain(
        fruitName = this.name ?: "",
        // nutritions = this.nutritions ?: Nutritions(),
        family = this.family ?: "",
        genus = this.genus ?: ""
    )
