package com.example.auth.data.network

actual class AuthApiProvider actual constructor() {
    actual companion object {
        actual fun create(): AuthApi = AuthApiImpl()
    }


}