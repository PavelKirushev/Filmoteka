package com.example.auth.presentation

import com.example.auth.domain.models.User
import com.example.auth.domain.models.UserInfo

sealed interface AuthSideEffects {
    data class RegisterUser(val user: User) : AuthSideEffects
    data class LoginUser(val userInfo: UserInfo) : AuthSideEffects
}