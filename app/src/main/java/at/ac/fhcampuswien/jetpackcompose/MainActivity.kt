package at.ac.fhcampuswien.jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.ac.fhcampuswien.jetpackcompose.widgets.navigation.MovieNavigation
import at.ac.fhcampuswien.jetpackcompose.ui.theme.JetPackComposeTheme
import at.ac.fhcampuswien.jetpackcompose.viewmodel.MovieViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieNavigation()
                }
            }
        }
    }

    /*
    override fun onStart() {
        super.onStart()
        Log.i("Main Activity", "OnStart Method called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Main Activity", "OnRestart Method called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Main Activity", "OnResume Method called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Main Activity", "OnPause Method called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Main Activity", "OnDestroy Method called")
    }*/
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackComposeTheme {
        MovieNavigation()
    }
}