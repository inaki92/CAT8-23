package com.example.acronymappcompose.model


import com.google.gson.annotations.SerializedName

data class AbbreviationItem(
    @SerializedName("lfs")
    val lfs: List<Lf>? = null,
    @SerializedName("sf")
    val sf: String? = null
)