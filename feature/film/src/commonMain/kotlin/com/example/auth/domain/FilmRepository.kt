package com.example.auth.domain

interface FilmRepository {
    suspend fun getFilm(filmId: Int): FilmDetails
}