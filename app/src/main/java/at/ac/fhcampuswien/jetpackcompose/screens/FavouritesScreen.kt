package at.ac.fhcampuswien.jetpackcompose.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import at.ac.fhcampuswien.jetpackcompose.models.Movie
import at.ac.fhcampuswien.jetpackcompose.models.getMovies
import at.ac.fhcampuswien.jetpackcompose.viewmodel.MovieViewModel
import at.ac.fhcampuswien.jetpackcompose.widgets.MovieRow

@Composable
fun FavouritesScreen(navController: NavController, movies : List<Movie>, movieViewModel: MovieViewModel) {
    Scaffold (
        topBar = { TopBar(title = "Favourite Movies", navController = navController) }
            ) {
        LazyColumn {
            for(movie in movies) {
                item {
                    MovieRow(movie = movie,  onFavouriteClick = { movieViewModel.addMovieToList(movie = movie) },
                        content = { Icon(imageVector = if(it) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = null) },
                        inList = movieViewModel.checkMovie(movie = movie))
                }
            }
        }
    }
}