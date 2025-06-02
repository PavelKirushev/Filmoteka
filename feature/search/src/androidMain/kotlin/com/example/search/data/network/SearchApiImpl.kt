package com.example.search.data.network

import com.example.search.SearchResults
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

class SearchApiImpl(private val apiKey: String) : SearchApi {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun getSearchResult(keyword: String, page: Int): SearchResults {
        return client.get {
            url("https://kinopoiskapiunofficial.tech/api/v2.1/films/search-by-keyword")
            header("X-API-KEY", apiKey)
            parameter("keyword", keyword)
            parameter("page", page)
        }.body()
    }
}