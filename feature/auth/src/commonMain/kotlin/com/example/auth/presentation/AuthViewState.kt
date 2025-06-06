package com.example.auth.presentation

import com.example.auth.domain.models.User

sealed class AuthViewState {
    object Init : AuthViewState()
    object Loading : AuthViewState()
    data class Error(val message: String) : AuthViewState()
    data class Success(val user: User) : AuthViewState()
    object ShowRegisterDialog : AuthViewState()
    object HideRegisterDialog : AuthViewState()
}