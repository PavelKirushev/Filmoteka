package com.example.film.data.network

import com.example.film.data.network.models.Film
import com.example.film.data.network.models.FilmVideos
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class FilmApiImpl(private val apiKey: String) : FilmApi {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun getFilmById(filmId: Int): Film {
        return client.get {
            url("https://kinopoiskapiunofficial.tech/api/v2.2/films/$filmId")
            header("X-API-KEY", apiKey)
        }.body()
    }

    override suspend fun getMovieById(filmId: Int): FilmVideos {
        return client.get {
            url("https://kinopoiskapiunofficial.tech/api/v2.2/films/$filmId/videos")
            header("X-API-KEY", apiKey)
        }.body()
    }

}