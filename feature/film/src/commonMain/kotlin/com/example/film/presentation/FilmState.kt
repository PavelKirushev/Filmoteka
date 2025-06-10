package com.example.film.presentation

import com.example.film.domain.models.FilmDetails

data class FilmState(
    val query: String = "",
    val isLoading: Boolean = false,
    val film: FilmDetails? = null,
    val error: String? = null
)
