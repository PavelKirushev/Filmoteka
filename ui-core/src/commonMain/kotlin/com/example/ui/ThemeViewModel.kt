package com.example.ui

import androidx.lifecycle.ViewModel

expect class ThemeViewModel : ViewModel {
    fun toggleTheme()

}