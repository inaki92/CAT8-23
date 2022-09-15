package com.example.fruitsappmvvm.model

/**
 * {
"genus": "Fragaria",
"name": "Strawberry",
"family": "Rosaceae",
"order": "Rosales",
"nutritions": {
"carbohydrates": 5.5,
"protein": 0,
"fat": 0.4,
"calories": 29,
"sugar": 5.4
}
}
 */
data class Fruit(
    val genus: String,
    val name: String,
    val family: String,
    val order: String,
    val nutritions: Nutritions
)
