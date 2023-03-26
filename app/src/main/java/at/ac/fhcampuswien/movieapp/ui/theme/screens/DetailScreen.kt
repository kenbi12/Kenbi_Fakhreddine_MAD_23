package at.ac.fhcampuswien.movieapp.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.movieapp.models.Movie
import at.ac.fhcampuswien.movieapp.models.getMovies
import at.ac.fhcampuswien.movieapp.ui.theme.widgets.DetailImages
import at.ac.fhcampuswien.movieapp.ui.theme.widgets.MovieRow


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController = rememberNavController(), movieId: String? = getMovies()[0].id ){ //(movieId: String? = getMovies()[0].id)
    val movie = filterMovie(movieId = movieId)


    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp) {
            Row() {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = movie.title, style = MaterialTheme.typography.h6)
            }
        }
    }
    ){
        MainContent(movie = movie)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(movie: Movie){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column() {
            MovieRow(movie = movie)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Movie Images",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6
                    )
                DetailImages(movie = movie)
            }

        }
    }
}

fun filterMovie(movieId: String?): Movie{
    return getMovies().filter { movie -> movie.id == movieId}[0]
}





