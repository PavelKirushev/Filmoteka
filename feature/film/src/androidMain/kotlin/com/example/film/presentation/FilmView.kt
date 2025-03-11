package com.example.film.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.film.data.FilmRepositoryImpl
import com.example.film.data.network.RetrofitHelper
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

class FilmView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = RetrofitHelper.filmApi
        val viewModel = FilmViewModel(FilmRepositoryImpl(retrofit))
        setContent {
            FilmScreen(viewModel)
        }
    }
}

@Composable
fun FilmScreen(viewModel: FilmViewModel) {
    val filmState by viewModel.film.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadFilm(303)
    }
    Column (modifier = Modifier
        .padding(20.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center){
        if (filmState != null) {
            AsyncImage(
                model = filmState?.imageUrl,
                contentDescription = "Image for film"
            )
            Text(filmState?.nameRu ?: "Название недоступно", fontSize = 16.sp)
            Text(filmState?.nameOriginal ?: "Название недоступно", fontSize = 16.sp)
            Text(filmState?.description ?: "Название недоступно", fontSize = 16.sp)
            Text(filmState?.rating.toString(), fontSize = 16.sp)
            Text(filmState?.countries.toString(), fontSize = 16.sp)
            Text(filmState?.year.toString(), fontSize = 16.sp)
        } else {
            Text("Загрузка...")
        }
    }
}