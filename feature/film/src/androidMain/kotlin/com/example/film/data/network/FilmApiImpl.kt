package com.example.film.data.network

import com.example.film.data.network.models.Film
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okio.IOException

class FilmApiImpl(private val apiKey: String) : FilmApi {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun getFilmById(filmId: Int): Film {
        try {
            return client.get {
                url("https://kinopoiskapiunofficial.tech/api/v2.2/films/$filmId")
                header("X-API-KEY", apiKey)
            }.body()
        } catch (e: Exception) {
            throw IOException("Не удалось загрузить данные")
        }

    }
}