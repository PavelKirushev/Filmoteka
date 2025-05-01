package com.example.auth.presentation.phone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.AuthRepository
import com.example.auth.domain.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(val authRepository: AuthRepository): ViewModel() {

    private val _auth = MutableStateFlow<User?>(null)
    val auth = _auth.asStateFlow()

    fun getUser(login: String, password: String) {
        viewModelScope.launch {
            try {
                _auth.value = authRepository.getUser(login, password)
            } catch (e: Exception) {

            }

        }
    }
}