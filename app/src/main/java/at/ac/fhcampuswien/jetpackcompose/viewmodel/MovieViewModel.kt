package at.ac.fhcampuswien.jetpackcompose.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import at.ac.fhcampuswien.jetpackcompose.models.Movie

class MovieViewModel : ViewModel() {
    private var fav_movies = mutableStateListOf<Movie>()

    fun addMovie(movie: Movie) {
        if(!fav_movies.contains(movie)) {
            fav_movies.add(movie)
        }
    }

    fun removeMovie(movie: Movie) {
        fav_movies.remove(movie)
    }

    fun getAllMovies() : List<Movie>{
        return fav_movies
    }

    fun checkMovie(movie: Movie) : Boolean {
        return fav_movies.contains(movie)
    }

    fun addMovieToList(movie : Movie) {
        if(checkMovie(movie = movie)) {
            removeMovie(movie = movie)
        } else {
            addMovie(movie = movie)
        }
    }
}