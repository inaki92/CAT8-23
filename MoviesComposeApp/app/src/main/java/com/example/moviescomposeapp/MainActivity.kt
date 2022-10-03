package com.example.moviescomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.moviescomposeapp.model.DomainMovie
import com.example.moviescomposeapp.ui.theme.MoviesComposeAppTheme
import com.example.moviescomposeapp.utils.UIState
import com.example.moviescomposeapp.viewmodel.MoviesViewModel

class MainActivity : ComponentActivity() {

    private val moviesViewModel by lazy {
        ViewModelProvider(this)[MoviesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Movies(
                        moviesViewModel.movies.observeAsState().value
                    )
                }
            }
        }
    }
}

@Composable
fun Movies(state: UIState?) {
    when (state) {
        is UIState.LOADING -> {}
        is UIState.SUCCESS -> {
            MovieItem(state.data)
        }
        is UIState.ERROR -> {

        }
    }
}

@Composable
fun MovieItem(data: List<DomainMovie>) {
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        itemsIndexed(items = data) { position, movie ->
            Movie(movie)
        }
    }
}

@Composable
fun Movie(movie: DomainMovie) {
    Card(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(30.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Surface() {
            Text(text = movie.name)
            Text(text = movie.category)
            Text(text = movie.description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesComposeAppTheme {
        MovieItem(data = listOf(
            DomainMovie(
                name = "movie1",
                description = "movie123",
                category = "category",
                poster = ""
            ),
            DomainMovie(
                name = "movie2",
            description = "movie123",
            category = "category",
            poster = ""
        )
        ))
    }
}