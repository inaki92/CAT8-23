package com.example.moviescomposeapp.utils

sealed class Routes(val route: String) {
    object MoviesScreen : Routes("Movies")
    object MovieDetails : Routes("MovieDetails")
}
