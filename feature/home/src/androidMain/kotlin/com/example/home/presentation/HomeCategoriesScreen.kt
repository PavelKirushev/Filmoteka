package com.example.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeCategoriesScreen(viewModel: HomeViewModel, controller: NavController) {
    val homeViewState by viewModel.viewStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.submitAction(HomeAction.LoadCategories)
    }

    when (val state = homeViewState) {
        is HomeViewState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is HomeViewState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.errorText ?: "Unknown Error")
            }
        }
        is HomeViewState.List -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Главное",
                        style = MaterialTheme.typography.displayMedium.copy(MaterialTheme.colorScheme.tertiary),
                        modifier = Modifier.padding(8.dp    ))
                }
                items(state.items) { category ->
                    Category(category, controller)
                }
            }
        }
    }
}
