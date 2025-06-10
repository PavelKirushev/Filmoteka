package com.example.auth.data.network

expect class AuthApiProvider() {
    companion object {
        fun create(): AuthApi
    }
}