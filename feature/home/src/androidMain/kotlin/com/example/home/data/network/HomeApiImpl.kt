package com.example.home.data.network

import com.example.example.CollectionsResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HomeApiImpl(private val apiKey: String) : HomeApi {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun getCollections(type: String, page: Int): CollectionsResult {
        return client.get {
            url("https://kinopoiskapiunofficial.tech/api/v2.2/films/collections")
            header("X-API-KEY", apiKey)
            parameter("type", type)
            parameter("page", page)
        }.body()
    }

}