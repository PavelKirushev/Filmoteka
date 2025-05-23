package com.example.auth.data.network

actual class AuthApiProvider actual constructor() {
    actual fun create(apiKey: String): AuthApi = AuthApiImpl()

}