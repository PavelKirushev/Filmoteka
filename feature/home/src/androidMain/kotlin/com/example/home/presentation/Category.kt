package com.example.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Category(category: CategoryDisplayItem, controller: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = category.title,
            style = MaterialTheme.typography.bodyLarge.copy(MaterialTheme.colorScheme.tertiary),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(category.films) { film ->
                FilmItem(film, controller)
            }
        }
    }
}