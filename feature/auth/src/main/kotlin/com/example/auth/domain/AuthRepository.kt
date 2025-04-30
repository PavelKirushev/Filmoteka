package com.example.auth.domain

interface AuthRepository {
    suspend fun getUser(login: String, password: String): User
}