package com.example.search.presentation.phone

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.search.R
import com.example.search.presentation.SearchAction
import com.example.search.presentation.SearchViewModel
import com.example.search.presentation.SearchViewState

@Composable
fun MainSearchScreen(viewModel: SearchViewModel, controller: NavHostController) {
    val searchViewState by viewModel.viewStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.submitAction(SearchAction.Init)
    }

    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Icon(
                androidx.compose.ui.res.painterResource(R.drawable.search_icon),
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            SimpleTextField(
                onValueChange = { newText ->
                    viewModel.submitAction(SearchAction.QueryChanged(newText))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )
        }
        when(searchViewState) {
            is SearchViewState.Loading -> {
                CircularProgressIndicator()
            }
            is SearchViewState.Error -> {

            }
            is SearchViewState.List -> {
                val films = (searchViewState as SearchViewState.List).items
                LazyColumn {
                    items(films) { film ->
                        FilmCard(film = film, controller = controller)
                        HorizontalDivider(color = MaterialTheme.colorScheme.primary, thickness = 0.5.dp)
                    }
                }
            }
        }
    }
}