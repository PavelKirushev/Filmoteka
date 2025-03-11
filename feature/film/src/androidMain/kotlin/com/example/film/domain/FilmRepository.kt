package com.example.film.domain

import com.example.film.data.FilmDetails

interface FilmRepository {
    suspend fun getFilm(filmId: Int): FilmDetails
}