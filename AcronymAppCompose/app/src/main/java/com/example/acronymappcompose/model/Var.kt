package com.example.acronymappcompose.model


import com.google.gson.annotations.SerializedName

data class Var(
    @SerializedName("freq")
    val freq: Int? = null,
    @SerializedName("lf")
    val lf: String? = null,
    @SerializedName("since")
    val since: Int? = null
)