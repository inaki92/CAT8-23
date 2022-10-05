package com.example.acronymappcompose.model


import com.google.gson.annotations.SerializedName

data class Lf(
    @SerializedName("freq")
    val freq: Int? = null,
    @SerializedName("lf")
    val lf: String? = null,
    @SerializedName("since")
    val since: Int? = null,
    @SerializedName("vars")
    val vars: List<Var>? = null
)