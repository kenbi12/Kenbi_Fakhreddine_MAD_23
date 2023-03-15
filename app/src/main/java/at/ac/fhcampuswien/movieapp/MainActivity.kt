package at.ac.fhcampuswien.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import at.ac.fhcampuswien.movieapp.ui.theme.MovieAppTheme
import at.ac.fhcampuswien.movieapp.ui.theme.navigation.MovieNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieNavigation()
                }

            }
        }
    }
}

















