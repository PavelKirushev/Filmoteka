package com.example.search.data.network

expect class SearchApiProvider() {
    companion object {
        fun create(apiKey: String): SearchApi
    }
}