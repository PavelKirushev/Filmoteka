package com.example.auth.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.auth.data.FilmRepositoryImpl
import com.example.auth.data.network.FilmApiProvider
import com.example.auth.presentation.phone.FilmScreen

class FilmScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val filmRetrofitClient = FilmApiProvider().create("1c4b2b3b-2536-40a4-a5c8-00666a9418c3")
                val filmViewModel = FilmViewModel(FilmRepositoryImpl(filmRetrofitClient))

                FilmScreen(
                    viewModel = filmViewModel,
                    id = 12412,
                    controller = rememberNavController()
                )
            }
        }
    }
}