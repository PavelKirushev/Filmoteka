package com.example.film.data.network

import com.example.film.data.network.models.Film

interface FilmApi {
    suspend fun getFilmById(filmId: Int): Film
}