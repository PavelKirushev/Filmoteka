package org.example.filmoteka

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.auth.presentation.phone.AuthScreen
import com.example.auth.presentation.phone.AuthViewModel
import com.example.film.presentation.phone.FilmScreen
import com.example.film.presentation.phone.FilmViewModel
import com.example.search.presentation.phone.mainsearchwindow.MainSearchScreen
import com.example.search.presentation.phone.mainsearchwindow.MainSearchViewModel
import com.example.ui_core.theme.ThemeViewModel

@Composable
fun Navigator(searchViewModel: MainSearchViewModel,
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
                FilmScreen(filmViewModel, filmId)
            }
        }
        composable("authScreen") {
            AuthScreen(authViewModel, themeViewModel)
        }
    }
}