package com.example.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

actual class ThemeViewModel : ViewModel() {
    private val _isDarkTheme: MutableState<Boolean> = mutableStateOf(false)
    val isDarkTheme: MutableState<Boolean> = _isDarkTheme

    actual fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }
}