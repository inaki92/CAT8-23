package com.example.yugiohmvvmcat23.model


import com.google.gson.annotations.SerializedName

data class CardSet(
    @SerializedName("set_code")
    val setCode: String? = null,
    @SerializedName("set_name")
    val setName: String? = null,
    @SerializedName("set_price")
    val setPrice: String? = null,
    @SerializedName("set_rarity")
    val setRarity: String? = null,
    @SerializedName("set_rarity_code")
    val setRarityCode: String? = null
)