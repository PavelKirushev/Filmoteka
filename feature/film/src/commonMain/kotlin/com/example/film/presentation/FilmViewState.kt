package com.example.film.presentation

import com.example.film.domain.models.FilmDetails

sealed class FilmViewState {
    data object Loading : FilmViewState()
    data object Empty : FilmViewState()
    data class Error(val errorText: String?) : FilmViewState()
    data class Film(val film: FilmDetails) : FilmViewState()
}