package com.example.auth.domain

import com.example.auth.domain.models.User
import com.example.auth.domain.models.UserInfo

interface AuthRepository {
    suspend fun getUser(userInfo: UserInfo): User
    suspend fun setUser(user: User)
}