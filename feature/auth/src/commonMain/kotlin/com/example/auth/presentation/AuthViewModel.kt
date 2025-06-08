package com.example.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.AuthRepository
import com.example.auth.domain.models.User
import com.example.auth.domain.models.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository): ViewModel() {

    private val mutableAuthStateFlow = MutableStateFlow<AuthViewState>(AuthViewState.Init)
    val authStateFlow = mutableAuthStateFlow.asStateFlow()

    private var state = AuthState()

    fun submitAction(action: AuthAction) {
        state = applyAction(action, state)
        mutableAuthStateFlow.value = createViewState(state)

        when (action) {
            is AuthAction.LoginButtonClicked -> {
                if (action.userInfo.login.isEmpty() || action.userInfo.password.isEmpty()) {
                    submitAction(AuthAction.Error("Заполните все поля"))
                } else {
                    val loginForm = action.userInfo
                    submitSideEffect(AuthSideEffects.LoginUser(loginForm))
                }
            }
            is AuthAction.RegisterButtonClicked -> {
                val registerForm = state.registerForm
                if (registerForm != null) {
                    submitSideEffect(AuthSideEffects.RegisterUser(registerForm))
                } else {
                    submitAction(AuthAction.Error("Заполните все поля"))
                }
            }
            AuthAction.HideRegisterDialog,
            AuthAction.ShowRegisterDialog,
            AuthAction.RegisterSuccess,
            is AuthAction.LoginSuccess,
            is AuthAction.Error -> Unit
        }
    }

    private fun applyAction(action: AuthAction, state: AuthState): AuthState {
        return when(action) {
            is AuthAction.Error -> state.copy(error = action.message, isLoading = false)
            AuthAction.HideRegisterDialog -> state.copy(showRegisterDialog = false, error = null)
            AuthAction.ShowRegisterDialog -> state.copy(showRegisterDialog = true, error = null)

            is AuthAction.LoginButtonClicked -> state.copy(isLoading = true, error = null)
            is AuthAction.RegisterButtonClicked -> state.copy(registerForm = action.user, isLoading = true, error = null)

            is AuthAction.LoginSuccess -> state.copy(currentUser = action.user, isLoading = false, error = null, isLogged = true)
            AuthAction.RegisterSuccess -> state.copy(isLoading = false, error = null)
        }
    }

    private fun createViewState(state: AuthState): AuthViewState {
        return when {
            state.isLoading -> AuthViewState.Loading
            !state.error.isNullOrBlank() -> AuthViewState.Error(state.error)
            !state.showRegisterDialog -> AuthViewState.HideRegisterDialog
            state.showRegisterDialog -> AuthViewState.ShowRegisterDialog
            state.currentUser != null -> AuthViewState.Success(state.currentUser)
            else -> AuthViewState.Init
        }
    }

    private fun submitSideEffect(sideEffect: AuthSideEffects) {
        when (sideEffect) {
            is AuthSideEffects.LoginUser -> loginUser(sideEffect.userInfo)
            is AuthSideEffects.RegisterUser -> registerUser(sideEffect.user)
        }
    }

    private fun loginUser(userInfo: UserInfo) {
        viewModelScope.launch {
            try {
                val user = authRepository.getUser(userInfo = userInfo)
                submitAction(AuthAction.LoginSuccess(user))
            } catch (e: Exception) {
                submitAction(AuthAction.Error(e.message ?: "Ошибка входа"))
            }
        }
    }

    private fun registerUser(user: User) {
        viewModelScope.launch {
            try {
                authRepository.setUser(user)
                submitAction(AuthAction.RegisterSuccess)
            } catch (e: Exception) {
                submitAction(AuthAction.Error(e.message ?: "Ошибка регистрации"))
            }
        }
    }
}