package at.ac.fhcampuswien.jetpackcompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import at.ac.fhcampuswien.jetpackcompose.models.Movie
import at.ac.fhcampuswien.jetpackcompose.models.getMovies
import at.ac.fhcampuswien.jetpackcompose.viewmodel.MovieViewModel
import at.ac.fhcampuswien.jetpackcompose.widgets.MovieRow
import coil.compose.AsyncImage

@Composable
fun DetailScreen(movieId: String, navController: NavHostController, movieViewModel: MovieViewModel) {
    val movie = filterMovie(movieId = movieId)
    MainContent(movie = movie, navController = navController) {
        LazyColumn {
            item {
                MovieRow(movie = movie,
                    onFavouriteClick = { movieViewModel.addMovieToList(movie = movie) },
                    content = { Icon(imageVector = if(it) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = null) },
                    inList = movieViewModel.checkMovie(movie = movie))
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Divider()
            }
            item {
                MovieImagesDisplay(movie = movie)
            }
        }
        //Text(text = movie.title, style = MaterialTheme.typography.h5)
    }
}

@Composable
fun MainContent(movie: Movie, navController: NavController, content: @Composable () -> Unit) {
    Scaffold (
        topBar = {
            TopBar(title = movie.title, navController = navController)
        }
            ) {
        content()
    }
}

@Composable
fun TopBar(title: String, navController: NavController) {
    TopAppBar(backgroundColor = Color.Red, elevation = 5.dp) {
        Row {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "ArrowBack",
                modifier = Modifier.clickable {
                    navController.popBackStack() //Go back to HomeScreen
                }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = title)
        }
    }
}

fun filterMovie(movieId: String) : Movie {
    return getMovies().filter{movie -> movie.id == movieId}[0]
}

@Composable
fun MovieImagesDisplay(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = 6.dp
    ) {
        Column(Modifier.padding(9.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Movie Images", style = MaterialTheme.typography.h5)

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow {
                for (image in movie.images) {
                    item {
                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}
