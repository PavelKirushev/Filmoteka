package com.example.home.presentation

import com.example.home.domain.FilmDetails

sealed class HomeViewState {
    data object Loading : HomeViewState()
    data class Error(val errorText: String?) : HomeViewState()
    data class List(
        val items: kotlin.collections.List<CategoryDisplayItem>
    ) : HomeViewState()
}


data class CategoryDisplayItem(
    val display: String,
    val query: String,
    val films: List<FilmDetails>,
    val isLoading: Boolean = false,
    val canLoadMore: Boolean = true,
    val page: Int = 1,
    val error: String? = null
)