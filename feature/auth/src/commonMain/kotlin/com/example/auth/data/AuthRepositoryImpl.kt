package com.example.auth.data

import com.example.auth.domain.models.User
import com.example.auth.domain.models.UserInfo
import com.example.auth.data.network.AuthApi
import com.example.auth.domain.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi
): AuthRepository {
    override suspend fun getUser(login: String, password: String): User = authApi.getUser(authApi.login(
        UserInfo(login, password)
    ))
    override suspend fun setUser(user: User) = authApi.setUser(user)
}