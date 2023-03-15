package at.ac.fhcampuswien.movieapp.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

import at.ac.fhcampuswien.movieapp.models.Movie
import at.ac.fhcampuswien.movieapp.models.getMovies

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen(movies: List<Movie> = getMovies()) {
    var showMenu by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "favorites"
                                )
                                Text(text = "Favorites")
                            }
                        }
                    }
                }
            )
        }
    ){
        LazyColumn {
            items(items = movies) { movie ->
                MovieRow(movie = movie)
            }

        }
    }
}
