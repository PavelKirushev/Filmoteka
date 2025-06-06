package com.example.auth.data

import com.example.auth.domain.models.User
import com.example.auth.domain.models.UserInfo
import com.example.auth.data.network.AuthApi
import com.example.auth.domain.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi
): AuthRepository {
    override suspend fun getUser(userInfo: UserInfo): User = authApi.getUser(authApi.login(userInfo))
    override suspend fun setUser(user: User) = authApi.setUser(user)
}