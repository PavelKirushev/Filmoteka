package com.example.film.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FilmApiProvider {
    val baseUrl = "https://kinopoiskapiunofficial.tech/"

    val filmApi: FilmApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FilmApi::class.java)
    }
}