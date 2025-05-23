package com.example.auth.data.network

actual class FilmApiProvider actual constructor() {
    actual fun create(apiKey: String): FilmApi = FilmApiImpl(apiKey)

}