package at.ac.fhcampuswien.jetpackcompose.widgets.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.ac.fhcampuswien.jetpackcompose.models.Movie
import at.ac.fhcampuswien.jetpackcompose.screens.HomeScreen
import at.ac.fhcampuswien.jetpackcompose.models.getMovies
import at.ac.fhcampuswien.jetpackcompose.screens.DetailScreen
import at.ac.fhcampuswien.jetpackcompose.screens.FavouritesScreen
import at.ac.fhcampuswien.jetpackcompose.viewmodel.MovieViewModel

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    val favMovieViewModel : MovieViewModel = viewModel()


    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(movies = getMovies(),
                navController = navController,
                movieViewModel = favMovieViewModel)
         }

        composable(
            route = MovieScreens.DetailScreen.name + "/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("movieId")
                ?.let { DetailScreen(movieId = it, navController = navController, movieViewModel = favMovieViewModel) }
        }

        composable(route = MovieScreens.FavouritesScreen.name) { FavouritesScreen(navController = navController, movies = favMovieViewModel.getAllMovies(),
            movieViewModel = favMovieViewModel)}
    }
}