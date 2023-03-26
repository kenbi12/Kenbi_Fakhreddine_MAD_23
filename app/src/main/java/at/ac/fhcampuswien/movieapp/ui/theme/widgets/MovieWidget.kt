package at.ac.fhcampuswien.movieapp.ui.theme.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.movieapp.models.Movie
import at.ac.fhcampuswien.movieapp.models.getMovies
import coil.compose.rememberAsyncImagePainter

@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = {}) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = movie.images[0]),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(50f / 19f)
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
                }
            }

            var show by remember {
                mutableStateOf(true)
            }

            if (show) {


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(movie.title, style = MaterialTheme.typography.h6)

                    IconButton(onClick = { show = !show }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "show details"
                        )
                    }
                }



            }
            if (!show)
                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(movie.title, style = MaterialTheme.typography.h6)

                        IconButton(onClick = { show = !show }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "hide details"
                            )
                        }
                    }

                    Column(modifier = Modifier.fillMaxWidth()) {

                        Column(modifier = Modifier.padding(10.dp)) {


                            Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                            Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)
                            Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.caption)
                            Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                            Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)
                        }
                        Column(modifier = Modifier.padding(10.dp)) {


                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(text = "Plot: ${movie.plot}", style = MaterialTheme.typography.caption)
                            }
                        }
                    }
                }
        }

    }
}

@Composable
fun DetailImages(movie: Movie = getMovies()[0]){
    LazyRow{
        items(movie.images){image ->
          Card(modifier = Modifier.padding(12.dp).size(250.dp)) {
              Image(painter = rememberAsyncImagePainter(model = image),
                  contentDescription = "movie images")
          }

        }
    }

}