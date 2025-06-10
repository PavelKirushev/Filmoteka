package com.example.auth.domain

import com.example.auth.domain.models.User
import kotlinx.coroutines.flow.StateFlow

interface AuthManager {
    val isLoggedInFlow: StateFlow<Boolean>
    fun isLoggedIn(): Boolean
    fun login(name: String, email: String, age: Int)
    fun logout()
    fun getUser(): User
    fun getAuthName(): String?
}