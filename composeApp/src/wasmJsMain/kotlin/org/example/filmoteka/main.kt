package org.example.filmoteka

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.compose.rememberNavController
import com.example.auth.data.FilmRepositoryImpl
import com.example.auth.data.network.FilmApiProvider
import com.example.auth.presentation.FilmScreen
import com.example.auth.presentation.FilmViewModel
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val filmRetrofitClient = FilmApiProvider().create("1c4b2b3b-2536-40a4-a5c8-00666a9418c3")
    val filmViewModel = FilmViewModel(FilmRepositoryImpl(filmRetrofitClient))
    ComposeViewport(document.body!!) {
        FilmScreen(filmViewModel, 12412, rememberNavController())
    }
}