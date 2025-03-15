package com.example.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.example.Films

@Composable
fun FilmCard(film: Films, controller: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = {
            controller.navigate("details/" + film.filmId)
        }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Постер фильма
            AsyncImage(
                model = film.posterUrlPreview ?: film.posterUrl,
                contentDescription = "Постер фильма ${film.nameRu}",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )

            // Информация о фильме
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                // Название и год
                Text(
                    text = "${film.nameRu ?: "Без названия"} (${film.year ?: "год не указан"})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Рейтинг
                film.rating?.let {
                    Text(
                        text = "Рейтинг: $it (${film.ratingVoteCount ?: 0} оценок)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                // Жанры
                if (film.genres.isNotEmpty()) {
                    Text(
                        text = "Жанры: ${film.genres.joinToString { it.genre ?: "" }}",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Страны
                if (film.countries.isNotEmpty()) {
                    Text(
                        text = "Страны: ${film.countries.joinToString { it.country ?: "" }}",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Тип (фильм/сериал и т.д.)
                film.type?.let {
                    Text(
                        text = it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Blue
                    )
                }
            }
        }
    }
}