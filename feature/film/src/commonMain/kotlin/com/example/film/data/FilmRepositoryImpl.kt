package com.example.film.data

import com.example.film.data.mappers.toFilmDetails
import com.example.film.domain.FilmDetails
import com.example.film.data.network.FilmApi
import com.example.film.domain.FilmRepository
import kotlinx.io.IOException


class FilmRepositoryImpl(
    private val filmApi: FilmApi
) : FilmRepository {
    override suspend fun getFilm(filmId: Int): FilmDetails {
        return filmApi.getFilmById(filmId).toFilmDetails()
    }
}