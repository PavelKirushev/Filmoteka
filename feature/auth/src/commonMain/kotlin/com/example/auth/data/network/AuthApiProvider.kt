package com.example.auth.data.network

expect class AuthApiProvider() {
    fun create(apiKey: String): AuthApi
}