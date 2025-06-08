package com.example.home.data.network

actual class HomeApiProvider actual constructor() {
    actual companion object {
        actual fun create(apiKey: String): com.example.home.data.network.HomeApi = HomeApiImpl(apiKey)
    }
}