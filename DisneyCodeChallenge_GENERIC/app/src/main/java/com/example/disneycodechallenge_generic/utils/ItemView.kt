package com.example.disneycodechallenge_generic.utils

import com.example.disneycodechallenge_generic.model.domain.DomainUser

sealed class ItemView {
    data class HeaderItem(val header: String): ItemView()
    data class UserItem(val user: DomainUser): ItemView()
}
