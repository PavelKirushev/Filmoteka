package com.example.search.presentation.phone

import android.util.Log
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.example.Films
import com.example.search.data.modelsfordomain.FilmDetails

@Composable
fun FilmCard(film: FilmDetails, controller: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = {
            controller.navigate("details/" + film.filmId)
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = film.posterUrl ?: film.posterUrlPreview,
                contentDescription = "Постер фильма ${film.nameRu}",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "${film.nameRu ?: "Без названия"} (${film.year ?: "год не указан"})",
                )
                if (film.rating.toString() != "null") {
                    Log.d("rating", film.rating.toString())
                    Text(
                        text = "Рейтинг: ${film.rating} (${film.ratingVoteCount ?: 0} оценок)",
                        style = MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.onTertiaryContainer)
                    )
                }


                if (film.genres.isNotEmpty()) {
                    Text(
                        text = "Жанры: ${film.genres.joinToString { it.genre ?: "" }}",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (film.countries.isNotEmpty()) {
                    Text(
                        text = "Страны: ${film.countries.joinToString { it.country ?: "" }}",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                film.type?.let {
                    Text(
                        text = if (it == "FILM") "Фильм" else "Сериал",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }
}