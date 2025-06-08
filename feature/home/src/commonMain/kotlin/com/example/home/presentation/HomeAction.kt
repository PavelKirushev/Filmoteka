package com.example.home.presentation

import com.example.home.domain.FilmDetails

sealed interface HomeAction {
    data object LoadCategories : HomeAction
    data class LoadCategorySuccess(val query: String, val films: List<FilmDetails>) : HomeAction
    data class LoadError(val errorMessage: String) : HomeAction
}