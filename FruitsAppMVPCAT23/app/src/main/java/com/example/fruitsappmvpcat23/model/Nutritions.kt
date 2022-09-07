package com.example.fruitsappmvpcat23.model


import com.google.gson.annotations.SerializedName

data class Nutritions(
    @SerializedName("calories")
    val calories: Int? = null,
    @SerializedName("carbohydrates")
    val carbohydrates: Double? = null,
    @SerializedName("fat")
    val fat: Double? = null,
    @SerializedName("protein")
    val protein: Double? = null,
    @SerializedName("sugar")
    val sugar: Double? = null
)