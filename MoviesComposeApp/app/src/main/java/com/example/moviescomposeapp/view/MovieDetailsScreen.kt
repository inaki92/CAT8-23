package com.example.moviescomposeapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviescomposeapp.model.DomainMovie
import com.example.moviescomposeapp.viewmodel.MoviesViewModel

@Composable
fun MovieDetails(moviesViewModel: MoviesViewModel, navController: NavController) {
    val movie = moviesViewModel.movie ?: DomainMovie(
        name = "Invalid name",
        category = "Invalid category",
        poster = "Invalid poster",
        description = "No description"
    )

    Scaffold(
        topBar = { CustomTopAppBar(navController = navController, label = "MovieDetails", true) },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = movie.name,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = movie.category,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(4.dp)
                )
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    )
}