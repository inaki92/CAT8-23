package com.example.moviescomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviescomposeapp.ui.theme.MoviesComposeAppTheme
import com.example.moviescomposeapp.utils.Routes
import com.example.moviescomposeapp.view.MovieDetails
import com.example.moviescomposeapp.view.Movies
import com.example.moviescomposeapp.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    ScreenMain(moviesViewModel)
                }
            }
        }
    }
}

@Composable
fun ScreenMain(moviesViewModel: MoviesViewModel) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Routes.MoviesScreen.route) {
        composable(Routes.MoviesScreen.route) {
            Movies(moviesViewModel, navController)
        }

        composable(Routes.MovieDetails.route) {
            MovieDetails(moviesViewModel, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesComposeAppTheme { }
}