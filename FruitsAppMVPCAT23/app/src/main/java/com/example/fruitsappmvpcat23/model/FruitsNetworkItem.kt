package com.example.fruitsappmvpcat23.model


import com.google.gson.annotations.SerializedName

data class FruitsNetworkItem(
    @SerializedName("family")
    val family: String? = null,
    @SerializedName("genus")
    val genus: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("nutritions")
    val nutritions: Nutritions? = null,
    @SerializedName("order")
    val order: String? = null
)