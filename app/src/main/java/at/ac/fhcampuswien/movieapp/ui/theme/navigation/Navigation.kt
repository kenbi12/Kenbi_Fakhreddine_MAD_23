package at.ac.fhcampuswien.movieapp.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.movieapp.ui.theme.screens.HomeScreen


@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
    }
}