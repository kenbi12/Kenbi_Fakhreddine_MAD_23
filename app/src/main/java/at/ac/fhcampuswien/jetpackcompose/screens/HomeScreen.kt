package at.ac.fhcampuswien.jetpackcompose.screens

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.jetpackcompose.models.Movie
import at.ac.fhcampuswien.jetpackcompose.widgets.navigation.MovieScreens
import at.ac.fhcampuswien.jetpackcompose.viewmodel.MovieViewModel
import at.ac.fhcampuswien.jetpackcompose.widgets.MovieRow

@Composable
fun HomeScreen(movies: List<Movie>, navController: NavHostController, movieViewModel: MovieViewModel) {
    Scaffold (
        topBar = { TopAppBar(navController = navController) }
    ) {
        LazyColumn {
            items(movies) { movie ->
                MovieRow(movie = movie,
                    onItemClick = { movieId -> navController.navigate(MovieScreens.DetailScreen.name + "/$movieId") },
                    onFavouriteClick = { movieViewModel.addMovieToList(it) },
                    inList = movieViewModel.checkMovie(movie = movie),
                    content = { Icon(imageVector = if(it) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = null) }
                )
            }
        }
    }
}



@Composable
fun TopAppBar(navController: NavHostController) {
    var showMenu by remember {
        mutableStateOf(false)
    }

    TopAppBar(title = {
        Text(text = "Movies")
    },
        backgroundColor = Color.Red,
        actions = {
            IconButton(onClick = {showMenu = !showMenu}) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = { navController.navigate(MovieScreens.FavouritesScreen.name) }) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favourites",
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text= "Favorites",
                            modifier = Modifier
                                .padding(4.dp)
                                .width(100.dp)
                        )
                    }
                }
            }
        }
    )
}