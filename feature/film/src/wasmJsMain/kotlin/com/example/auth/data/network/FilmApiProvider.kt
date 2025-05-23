package com.example.auth.data.network

import com.example.auth.data.network.models.Film
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class FilmApiProvider actual constructor() {
    actual fun create(apiKey: String): FilmApi = FilmApiImpl(apiKey)
}

class FilmApiImpl(private val apiKey: String) : FilmApi {
    private val client = HttpClient(Js) {
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
}