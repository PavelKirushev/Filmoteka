package com.example.film.domain

import com.example.film.data.modelsfordomain.FilmDetails

interface FilmRepository {
    suspend fun getFilm(filmId: Int): FilmDetails
}