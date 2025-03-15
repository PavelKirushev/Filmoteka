package com.example.search.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchApiProvider {
    val baseUrl = "https://kinopoiskapiunofficial.tech/"

    val searchApi: SearchApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)
    }
}