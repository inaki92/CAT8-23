package com.example.disneycodechallenge_generic.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val userName: String,
    @SerializedName("hasReservation")
    val hasReservation: Boolean
)
