package com.example.film.data

import com.example.film.data.mappers.toFilmDetails
import com.example.film.data.modelsfordomain.FilmDetails
import com.example.film.data.network.FilmApi
import com.example.film.domain.FilmRepository


class FilmRepositoryImpl(
    private val filmApi: FilmApi
): FilmRepository {
    override suspend fun getFilm(filmId: Int): FilmDetails {
        val response = filmApi.getFilmById(filmId, apiKey = "1c4b2b3b-2536-40a4-a5c8-00666a9418c3")
        return if (response.isSuccessful) {
            response.body()?.toFilmDetails() ?: throw Exception("Film not found")
        } else {
            throw Exception("Returning FilmDetails is null")
        }
    }
}