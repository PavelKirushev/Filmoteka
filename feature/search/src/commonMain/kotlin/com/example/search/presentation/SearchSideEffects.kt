package com.example.search.presentation

sealed class SearchSideEffects {
    data class LoadFilms(val query: String) : SearchSideEffects()
}