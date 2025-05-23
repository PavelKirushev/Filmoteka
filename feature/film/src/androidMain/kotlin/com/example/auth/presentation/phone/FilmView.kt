package com.example.auth.presentation.phone

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.auth.presentation.FilmViewModel

@Composable
fun FilmScreen(viewModel: FilmViewModel, id: Int, controller: NavController) {
    val filmState by viewModel.film.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadFilm(id)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
//            item {
//                TopAppBar(
//                    modifier = TODO(),
//                    navigationIcon = TODO(),
//                    actions = {
//                        IconButton(
//                            {
//                                controller.navigateUp()
//                            }
//                        ) {
//                            Icon(
//                                Icons.
//                            )
//                        }
//                    },
//                    expandedHeight = TODO(),
//                    windowInsets = TODO(),
//                    colors = TODO(),
//                    scrollBehavior = TODO()
//                )
//            }
            item {
                AsyncImage(
                    model = filmState?.imageUrl,
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .fillMaxWidth(0.6f),
                    contentScale = ContentScale.Crop,
//                    alignment = Al,
                )
            }
            item {
                filmState?.let { film ->
                    Text(
                        text = film.nameRu ?: "Название недоступно",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    film.nameOriginal?.let { originalName ->
                        Text(
                            text = originalName,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Row(modifier = Modifier.padding(bottom = 8.dp)) {
                        film.rating.let {
                            Text(
                                text = "Рейтинг: $it",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(end = 16.dp)
                            )
                        }
                        film.year?.let {
                            Text(
                                text = "Год: $it",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    film.countries?.takeIf { it.isNotEmpty() }?.let { countries ->
                        Text(
                            text = "Страны: ${countries.joinToString()}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    Text(
                        text = film.description ?: "Описание недоступно",
                        style = MaterialTheme.typography.bodyLarge
                    )
                } ?: run {
                    Text(
                        text = "Данные не загружены",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}