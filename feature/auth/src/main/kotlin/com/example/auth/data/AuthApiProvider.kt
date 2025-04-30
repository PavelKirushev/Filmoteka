package com.example.auth.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthApiProvider {
    val baseUrl = "http://10.0.2.2:8080"

    val authApi: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}