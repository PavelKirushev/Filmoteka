package org.example.filmoteka

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.auth.presentation.AuthViewModel
import com.example.auth.presentation.phone.AuthScreen
import com.example.film.presentation.FilmViewModel
import com.example.film.presentation.phone.FilmScreen
import com.example.search.presentation.SearchViewModel
import com.example.search.presentation.phone.MainSearchScreen
import com.example.ui.ThemeViewModel

@Composable
fun Navigator(searchViewModel: SearchViewModel,
              filmViewModel: FilmViewModel,
              authViewModel: AuthViewModel,
              controller: NavHostController,
              themeViewModel: ThemeViewModel
              ){
    NavHost(navController = controller, startDestination = "authScreen") {
        composable("searchScreen") { MainSearchScreen(searchViewModel, controller) }
        composable("details/{filmId}") {
            val filmId = it.arguments?.getString("filmId")?.toIntOrNull()
            if (filmId != null) {
                FilmScreen(filmViewModel, filmId, controller)
            }
        }
        composable("authScreen") {
            AuthScreen(authViewModel, themeViewModel)
        }
    }
}