package com.example.auth.presentation

import com.example.auth.domain.models.User

sealed class AuthViewState {
    data object Init : AuthViewState()
    data object Loading : AuthViewState()

    data object ShowRegisterDialog : AuthViewState()
    data object HideRegisterDialog : AuthViewState()

    data class Error(val message: String) : AuthViewState()
    data class Success(val user: User) : AuthViewState()
}