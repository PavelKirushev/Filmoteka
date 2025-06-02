package com.example.film.domain

interface FilmRepository {
    suspend fun getFilm(filmId: Int): FilmDetails
}