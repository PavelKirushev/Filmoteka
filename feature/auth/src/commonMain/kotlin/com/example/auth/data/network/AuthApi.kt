package com.example.auth.data.network

import com.example.auth.domain.models.Token
import com.example.auth.domain.models.User
import com.example.auth.domain.models.UserInfo

interface AuthApi {
    suspend fun getUser(token: Token): User
    suspend fun login(userInfo: UserInfo): Token
    suspend fun setUser(user: User)
}