package org.example.filmoteka

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.film.presentation.FilmScreen
import com.example.film.presentation.FilmView
import com.example.film.presentation.FilmViewModel
import com.example.search.presentation.mainsearchwindow.MainSearchScreen
import com.example.search.presentation.mainsearchwindow.MainSearchViewModel

@Composable
fun Navigator(searchViewModel: MainSearchViewModel, filmViewModel: FilmViewModel, controller: NavHostController, ){
    NavHost(navController = controller, startDestination = "searchScreen") {
        composable("searchScreen") { MainSearchScreen(searchViewModel, controller) }
        composable("details/{filmId}") {
            val filmId = it.arguments?.getString("filmId")?.toIntOrNull()
            if (filmId != null) {
                FilmScreen(filmViewModel, filmId)
            }

        }
    }
}