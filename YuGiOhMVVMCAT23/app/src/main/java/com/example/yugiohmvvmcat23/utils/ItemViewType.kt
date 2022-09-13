package com.example.yugiohmvvmcat23.utils

import com.example.yugiohmvvmcat23.model.domain.DomainCard

sealed class ItemViewType {
    data class HeaderItem(val letter: String) : ItemViewType()
    data class CardItem(val card: DomainCard) : ItemViewType()
}
