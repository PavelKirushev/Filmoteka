package com.example.home.presentation

sealed class HomeSideEffects {
    data class LoadCategory(val type: String, val page: Int) : HomeSideEffects()
}