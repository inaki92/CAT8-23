package com.example.moviescomposeapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.moviescomposeapp.R
import com.example.moviescomposeapp.model.DomainMovie
import com.example.moviescomposeapp.utils.Routes
import com.example.moviescomposeapp.utils.UIState
import com.example.moviescomposeapp.viewmodel.MoviesViewModel

@Composable
fun Movies(moviesViewModel: MoviesViewModel, navController: NavController) {
    when (val state = moviesViewModel.movies.observeAsState().value) {
        is UIState.LOADING -> {}
        is UIState.SUCCESS -> {
            MovieItem(state.data, navController, moviesViewModel)
        }
        is UIState.ERROR -> {
            ErrorDialog(message = state.error.localizedMessage, true)
        }
    }
}

@Composable
fun MovieItem(
    data: List<DomainMovie>,
    navController: NavController,
    moviesViewModel: MoviesViewModel? = null
) {
    Scaffold(
        topBar = { CustomTopAppBar(navController = navController, label = "Movies") },
        content = {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                itemsIndexed(items = data) { position, movie ->
                    Movie(movie, navController, moviesViewModel)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Movie(movie: DomainMovie, navController: NavController, moviesViewModel: MoviesViewModel?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = {
            moviesViewModel?.movie = movie
            navController.navigate(Routes.MovieDetails.route)
        }
    ) {
        Surface {
            Row {
                Image(painter = rememberImagePainter(
                    request = ImageRequest.Builder(LocalContext.current)
                        .data(movie.poster)
                        .scale(Scale.FILL)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .transformations(CircleCropTransformation())
                        .build()
                ),
                    contentDescription = movie.description,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
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
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}