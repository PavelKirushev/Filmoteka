package com.example.auth.presentation

import com.example.auth.domain.models.Token
import com.example.auth.domain.models.User
import com.example.auth.domain.models.UserInfo

data class AuthState(
    val showRegisterDialog: Boolean = false,
    val isLoading: Boolean = false,

    val loginForm: UserInfo? = null,
    val registerForm: User? = null,

    val isLogged: Boolean = false,
    val currentUser: User? = null,
    val error: String? = null
)