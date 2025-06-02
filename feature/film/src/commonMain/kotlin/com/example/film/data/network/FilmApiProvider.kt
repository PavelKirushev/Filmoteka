package com.example.film.data.network

expect class FilmApiProvider() {

    companion object {
        fun create(apiKey: String): FilmApi

    }
}