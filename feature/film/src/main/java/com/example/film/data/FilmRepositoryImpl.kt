package com.example.film.data

import com.example.film.data.network.FilmApi
import com.example.film.domain.FilmRepository

class FilmRepositoryImpl(
    private val filmApi: FilmApi
): FilmRepository {
    override suspend fun getFilm(filmId: Int, apiKey: String): FilmDetails {
        val response = filmApi.getFilmById(filmId, apiKey)
        return if (response.isSuccessful) {
            response.body() ?: throw FilmNotFoundException("Film not found")
        } else {
           throw FilmApiException("Returning FilmDetails is null")
        }
    }
}

class FilmNotFoundException(message: String) : Exception(message)
class FilmApiException(message: String) : Exception(message)