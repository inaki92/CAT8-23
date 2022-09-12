package com.example.yugiohmvvmcat23.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("archetype")
    val archetype: String? = null,
    @SerializedName("banlist_info")
    val banlistInfo: BanlistInfo? = null,
    @SerializedName("card_images")
    val cardImages: List<CardImage?>? = null,
    @SerializedName("card_prices")
    val cardPrices: List<CardPrice?>? = null,
    @SerializedName("card_sets")
    val cardSets: List<CardSet?>? = null,
    @SerializedName("desc")
    val desc: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("race")
    val race: String? = null,
    @SerializedName("type")
    val type: String? = null
)