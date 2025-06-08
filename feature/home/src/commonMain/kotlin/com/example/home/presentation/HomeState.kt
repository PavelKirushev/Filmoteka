package com.example.home.presentation

import com.example.home.domain.FilmDetails

data class HomeState(
    val categories: List<CategoryState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class CategoryState(
    val query: String,
    val title: String,
    val films: List<FilmDetails>,
    val isLoading: Boolean = false,
    val page: Int = 1,
    val error: String? = null
)