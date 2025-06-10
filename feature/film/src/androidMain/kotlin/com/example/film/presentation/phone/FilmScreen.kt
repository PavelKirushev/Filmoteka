package com.example.film.presentation.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Нет данных")
            }
        }
        is FilmViewState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = filmViewState.errorText ?: "Произошла ошибка")
            }
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
                                .aspectRatio(2f / 3f)
                                .clip(MaterialTheme.shapes.medium)
                                .shadow(8.dp, MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop,
                        )
                    }
                    item {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = film.nameRu ?: "Название недоступно",
                                style = MaterialTheme.typography.displaySmall.copy(MaterialTheme.colorScheme.onPrimaryContainer),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            film.nameOriginal?.let { originalName ->
                                Text(
                                    text = originalName,
                                    style = MaterialTheme.typography.headlineSmall.copy(MaterialTheme.colorScheme.primary),
                                    modifier = Modifier.padding(bottom = 20.dp)
                                )
                            }
                            Row(
                                modifier = Modifier.padding(bottom = 12.dp).fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "Рейтинг: ${film.rating ?: "—"}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                                film.year?.let {
                                    Text(
                                        text = "Год: $it",
                                        style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.primary),
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                }
                            }
                        }

                        Text(
                            text = film.description ?: "Описание недоступно",
                            style = MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.primary),
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        film.countries.takeIf { it.isNotEmpty() }?.let {
                            Text(
                                text = "Страны: ${film.countries.joinToString { it.country ?: "" }}",
                                style = MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.primary),
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }
                    }
                }
            }
        }

        FilmViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    CircularProgressIndicator()
                    Text(text = "Загрузка")
                }

            }
        }
    }
}
