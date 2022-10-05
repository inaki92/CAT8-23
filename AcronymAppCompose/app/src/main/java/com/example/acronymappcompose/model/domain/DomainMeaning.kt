package com.example.acronymappcompose.model.domain

import com.example.acronymappcompose.model.AbbreviationItem

data class DomainMeaning(
    val meaning: String,
    val totalFrequency: Int,
    val since: Int
)

fun List<AbbreviationItem>.mapToDomainMeaning(): List<DomainMeaning> =
    this.firstOrNull()?.lfs?.map {
        DomainMeaning(
            meaning = it.lf ?: "",
            totalFrequency = it.freq ?: 0,
            since = it.since ?: 0
        )
    } ?: emptyList()
