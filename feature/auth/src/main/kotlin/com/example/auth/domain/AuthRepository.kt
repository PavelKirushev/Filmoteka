package com.example.auth.domain

import com.example.auth.data.models.User

interface AuthRepository {
    suspend fun getUser(login: String, password: String): User
}