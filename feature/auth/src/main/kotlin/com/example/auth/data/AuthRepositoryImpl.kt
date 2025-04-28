package com.example.auth.data

import com.example.auth.data.models.User
import com.example.auth.data.models.UserInfo
import com.example.auth.domain.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi
): AuthRepository {
    override suspend fun getUser(login: String, password: String): User {
        val token = authApi.login(UserInfo(login, password))
        val response = token.body()?.let {
            authApi.getUser(it.token)
        }
        if (response != null) {
            return response.body() ?: throw Exception("User not found")
        } else {
            throw Exception("")
        }
    }
}