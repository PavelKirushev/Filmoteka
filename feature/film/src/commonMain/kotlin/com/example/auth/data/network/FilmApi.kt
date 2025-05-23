package com.example.auth.data.network

import com.example.auth.data.network.models.Film

interface FilmApi {
    suspend fun getFilmById(filmId: Int): Film
}