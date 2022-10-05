package com.example.acronymappcompose

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.acronymappcompose.model.domain.DomainMeaning
import com.example.acronymappcompose.ui.theme.AcronymAppComposeTheme
import com.example.acronymappcompose.utils.UIState
import com.example.acronymappcompose.viewmodel.AcronymViewModel

class MainActivity : ComponentActivity() {

    private val acronymViewModel by lazy {
        ViewModelProvider(this)[AcronymViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcronymAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { SearchViewAcronym() }
                ) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                    ) {
                        SearchViewBar(acronymViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchViewAcronym() {
    TopAppBar(
        title = { Text(text = "Acronym Search Engine", fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.teal_200),
        contentColor = Color.White
    )
}

@Composable
fun SearchViewBar(viewModel: AcronymViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textState.value,
            onValueChange = {
                textState.value = it
                viewModel.getMeaningOf(it.text)
            },
            label = { Text(text = "Search Acronym") },
            singleLine = true,
            shape = RectangleShape,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            }
        )

        Spacer(modifier = Modifier.size(10.dp))

        MeaningsScreen(uiState = viewModel.meanings.observeAsState().value)
    }
}

@Composable
fun MeaningsScreen(uiState: UIState?) {
    when (uiState) {
        is UIState.LOADING -> {}
        is UIState.SUCCESS -> {
            Meanings(meanings = uiState.data)
        }
        is UIState.ERROR -> {

        }
    }
}

@Composable
fun Meanings(meanings: List<DomainMeaning>) {
    LazyColumn {
        itemsIndexed(items = meanings) { position, meaning ->
            MeaningItem(meaning = meaning)
        }
    }
}

@Composable
fun MeaningItem(meaning: DomainMeaning) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column {
            Text(text = meaning.meaning)
            Text(text = meaning.totalFrequency.toString())
            Text(text = meaning.since.toString())
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AcronymAppComposeTheme {
//        Scaffold(
//            topBar = { SearchViewAcronym() }
//        ) {
//            Box(
//                modifier = Modifier
//                    .padding(it)
//            ) {
//                SearchViewBar()
//            }
//        }
    }
}