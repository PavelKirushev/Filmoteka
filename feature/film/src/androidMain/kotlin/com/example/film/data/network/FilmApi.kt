package com.example.film.data.network

import com.example.film.data.network.models.Film
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FilmApi {
    @GET("api/v2.2/films/{id}")
    suspend fun getFilmById(
        @Path("id") filmId: Int,
        @Header("X-API-KEY") apiKey: String,
    ): Response<Film>
}