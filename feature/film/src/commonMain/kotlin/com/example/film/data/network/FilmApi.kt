package com.example.film.data.network

import com.example.film.data.network.models.Film
import com.example.film.data.network.models.FilmVideos

interface FilmApi {
    suspend fun getFilmById(filmId: Int): Film
    suspend fun getMovieById(filmId: Int): FilmVideos
}