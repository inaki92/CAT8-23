package com.example.yugiohmvvmcat23.model


import com.google.gson.annotations.SerializedName

data class CardPrice(
    @SerializedName("amazon_price")
    val amazonPrice: String? = null,
    @SerializedName("cardmarket_price")
    val cardmarketPrice: String? = null,
    @SerializedName("coolstuffinc_price")
    val coolstuffincPrice: String? = null,
    @SerializedName("ebay_price")
    val ebayPrice: String? = null,
    @SerializedName("tcgplayer_price")
    val tcgplayerPrice: String? = null
)