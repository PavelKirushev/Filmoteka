package com.example.auth.presentation

import com.example.auth.domain.models.User
import com.example.auth.domain.models.UserInfo

sealed interface AuthAction {
    // Пользовательские взаимодействия
    data object ShowRegisterDialog : AuthAction
    data object HideRegisterDialog : AuthAction

    data object LoginButtonClicked : AuthAction
    data class RegisterButtonClicked(val user: User) : AuthAction

    // Системные события
    data class LoginSuccess(val user: User) : AuthAction
    data object RegisterSuccess : AuthAction
    data class Error(val message: String) : AuthAction
}