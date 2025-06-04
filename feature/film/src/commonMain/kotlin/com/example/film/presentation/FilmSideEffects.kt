package com.example.film.presentation

sealed interface FilmSideEffects {
    data class LoadFilm(val query: String) : FilmSideEffects
}