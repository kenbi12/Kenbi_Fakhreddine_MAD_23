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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import at.ac.fhcampuswien.movieapp.models.Movie
import at.ac.fhcampuswien.movieapp.models.getMovies
import at.ac.fhcampuswien.movieapp.ui.theme.navigation.MovieScreens
import at.ac.fhcampuswien.movieapp.ui.theme.widgets.MovieRow


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen(navController : NavHostController = rememberNavController() ) {
    var favorites by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = {  navController.navigate(MovieScreens.FavoritesScreen.name)
                            favorites = false  }) {
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
        }){
      MainContent(navController = navController)
    }
}
@Composable
fun MainContent (movies: List<Movie> = getMovies(), navController: NavHostController){
    LazyColumn {
        items(movies) {movie ->
            MovieRow(movie = movie) { movieId ->
                    navController.navigate(MovieScreens.DetailScreen.name + "/$movieId")
                }
        }

    }
}