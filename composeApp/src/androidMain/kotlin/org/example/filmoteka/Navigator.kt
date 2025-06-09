package org.example.filmoteka

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.auth.domain.AuthManager
import com.example.auth.presentation.AuthAction
import com.example.auth.presentation.AuthViewModel
import com.example.auth.presentation.phone.AuthScreen
import com.example.film.presentation.FilmViewModel
import com.example.film.presentation.phone.FilmScreen
import com.example.home.presentation.HomeCategoriesScreen
import com.example.home.presentation.HomeViewModel
import com.example.search.presentation.SearchViewModel
import com.example.search.presentation.phone.MainSearchScreen
import com.example.ui.ThemeViewModel

@Composable
fun Navigator(searchViewModel: SearchViewModel,
              filmViewModel: FilmViewModel,
              authViewModel: AuthViewModel,
              homeViewModel: HomeViewModel,
              controller: NavHostController,
              themeViewModel: ThemeViewModel,
              authManager: AuthManager
              ){

    val isLoggedIn by authManager.isLoggedInFlow.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            try {
                controller.navigate("homeScreen") {
                    popUpTo("authScreen") { inclusive = true }
                }
            } catch (e: Exception) {
                authViewModel.submitAction(AuthAction.Error("Ошибка: ${e.message}"))
            }

        }
    }

    NavHost(navController = controller, startDestination = "authScreen") {
        composable("searchScreen") {
            MainSearchScreen(searchViewModel, controller)
        }
        composable("details/{filmId}") {
            val filmId = it.arguments?.getString("filmId")
            if (filmId != null) {
                filmViewModel.loadFilm(filmId)
                val filmViewState by filmViewModel.filmStateFlow.collectAsState()
                val isRefreshing by filmViewModel.isRefreshing.collectAsState()

                FilmScreen(
                    filmViewState = filmViewState,
                    isRefreshing = isRefreshing,
                    onRefresh = { filmViewModel.refreshFilm() }
                )
            }
        }
        composable("authScreen") {
            AuthScreen(authViewModel, themeViewModel, navigateHome = { controller.navigate("homeScreen") }, authManager = authManager)
        }
        composable("homeScreen") {
            HomeCategoriesScreen(homeViewModel, controller)
        }
    }
}