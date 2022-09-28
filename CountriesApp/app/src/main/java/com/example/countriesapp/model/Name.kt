package com.example.countriesapp.model


import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("common")
    val common: String? = "",
    @SerializedName("nativeName")
    val nativeName: NativeName? = NativeName(),
    @SerializedName("official")
    val official: String? = ""
)