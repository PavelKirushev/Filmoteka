package com.example.film.domain

import com.example.film.domain.models.FilmDetails

interface FilmRepository {
    suspend fun getFilm(filmId: Int): FilmDetails
}