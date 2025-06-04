package com.example.film.presentation.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.film.presentation.FilmViewState

import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FilmScreen(
    filmViewState: FilmViewState,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    when (filmViewState) {
        FilmViewState.Empty -> {
            TextOnFullScreen(text = "Нет данных")
        }
        is FilmViewState.Error -> {
            TextOnFullScreen(text = "Произошла ошибка")
        }
        is FilmViewState.Film -> {
            val film = filmViewState.film
            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = onRefresh,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        AsyncImage(
                            model = film.imageUrl,
                            contentDescription = "Movie Poster",
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(2f / 3f) // Киноафиша обычно 2:3
                                .clip(MaterialTheme.shapes.medium)
                                .shadow(8.dp, MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    item {
                        Text(
                            text = film.nameRu ?: "Название недоступно",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        film.nameOriginal?.let { originalName ->
                            Text(
                                text = originalName,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }

                        Row(
                            modifier = Modifier.padding(bottom = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            Text(
                                text = "Рейтинг: ${film.rating ?: "—"}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            film.year?.let {
                                Text(
                                    text = "Год: $it",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        film.countries.takeIf { it.isNotEmpty() }?.let { countries ->
                            Text(
                                text = "Страны: ${film.countries.joinToString { it.country ?: "" }}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }

                        Text(
                            text = film.description ?: "Описание недоступно",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                    }
                }
            }
        }
        FilmViewState.Loading -> {
            TextOnFullScreen(text = "Загрузка", showIndicator = true)
        }
    }
}


@Composable
fun TextOnFullScreen(text: String, showIndicator: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        if (showIndicator) {
            CircularProgressIndicator()
        }
        Text(text = text, style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.primary))
    }
}