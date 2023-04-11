package at.ac.fhcampuswien.jetpackcompose.widgets

import android.graphics.drawable.Icon
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.jetpackcompose.models.Movie
import coil.compose.AsyncImage

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie,
             onItemClick: (String) -> Unit = {},
             onFavouriteClick: (Movie) -> Unit = {},
             inList : Boolean,
             content : @Composable (Boolean) -> Unit = {}) {
    var showInfo by remember {
        mutableStateOf(false)
    }
    var liked by remember {
        mutableStateOf(inList)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(4.dp),
        elevation = 6.dp,
    ) {
        Row(modifier = Modifier
            .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

            ) {
            Surface(
                Modifier
                    .size(90.dp)) {
                AsyncImage(model = movie.images[0], contentDescription = null, modifier = Modifier.clip(
                    CircleShape))
            }
            Column(
                Modifier.padding(9.dp)
            ) {
                Text(text = movie.title, Modifier.padding(0.dp,0.dp,0.dp,6.dp), fontWeight = FontWeight.Bold)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.subtitle1)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.subtitle1)
                Icon(
                    imageVector = selectIcon(showInfo),
                    contentDescription = "More Information",
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable { showInfo = !showInfo }
                )
                AnimatedVisibility(
                    visible = showInfo,
                    enter = slideInVertically() + expandVertically() + fadeIn(),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut()
                ) {
                    ViewExtraInformation(movie = movie)
                }
            }
            IconButton(
                content = { content(liked) },
                onClick = {
                    onFavouriteClick(movie)
                    liked = !liked
                },
            )
        }
    }
}

fun selectIcon(show : Boolean) : ImageVector {
    return if(show) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
}

@Composable
fun ViewExtraInformation(movie: Movie) {
    Column {
        Text(text = "Plot: ${movie.plot}", style = MaterialTheme.typography.subtitle2)
        Divider(color = Color.Gray, thickness = 1.dp)
        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.subtitle2)
        Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.subtitle2)
        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.subtitle2)
    }
}