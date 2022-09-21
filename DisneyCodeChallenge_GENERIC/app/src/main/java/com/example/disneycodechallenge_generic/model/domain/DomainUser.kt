package com.example.disneycodechallenge_generic.model.domain

import com.example.disneycodechallenge_generic.model.User

data class DomainUser(
    val userName: String,
    val hasReservation: Boolean
)

fun List<User>.mapToDomainUser(): List<DomainUser> =
    this.map {
        DomainUser(
            userName = it.userName,
            hasReservation = it.hasReservation
        )
    }
