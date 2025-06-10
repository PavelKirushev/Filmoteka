package com.example.home.data.network

expect class HomeApiProvider() {
    companion object {
        fun create(apiKey: String): HomeApi
    }
}