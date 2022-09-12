package com.example.yugiohmvvmcat23.model


import com.google.gson.annotations.SerializedName

data class BanlistInfo(
    @SerializedName("ban_goat")
    val banGoat: String? = null,
    @SerializedName("ban_ocg")
    val banOcg: String? = null,
    @SerializedName("ban_tcg")
    val banTcg: String? = null
)