package com.example.moviescomposeapp.view

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun CustomTopAppBar(
    navController: NavController,
    label: String,
    showBackNav: Boolean = false
) {
    TopAppBar(
        title = { Text(text = label) },
        navigationIcon =
        if (showBackNav && navController.previousBackStackEntry != null) {
            {
                IconButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }
    )
}

@Composable
fun ErrorDialog(message: String, stateError: Boolean = false) {
    val state = remember { mutableStateOf(stateError) }

    if (state.value) {
        AlertDialog(
            title = { Text(text = "Error has occurred") },
            text = { Text(text = message) },
            confirmButton = {
                Button(
                    onClick = { state.value = false }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { state.value = false }
                ) {
                    Text(text = "DISMISS")
                }
            },
            onDismissRequest = {  }
        )
    }
}