package com.example.auth.data

import com.example.auth.data.mappers.toFilmDetails
import com.example.auth.domain.FilmDetails
import com.example.auth.data.network.FilmApi
import com.example.auth.domain.FilmRepository


class FilmRepositoryImpl(
    private val filmApi: FilmApi
) : FilmRepository {
    override suspend fun getFilm(filmId: Int): FilmDetails = filmApi.getFilmById(filmId).toFilmDetails()
}