package com.example.auth.domain

import com.example.auth.domain.models.User

interface AuthRepository {
    suspend fun getUser(login: String, password: String): User
    suspend fun setUser(user: User)
}