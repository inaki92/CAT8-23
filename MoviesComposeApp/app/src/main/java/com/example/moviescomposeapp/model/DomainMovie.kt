package com.example.moviescomposeapp.model

data class DomainMovie(
    val name: String,
    val poster: String,
    val category: String,
    val description: String
    )

fun List<MoviesItem>?.mapToDomainItems() :List<DomainMovie> =
    this?.map {
        DomainMovie(
            name = it.name ?: "",
            poster = it.imageUrl ?: "",
            category = it.category ?: "",
            description = it.desc ?: ""
        )
    } ?: emptyList()
