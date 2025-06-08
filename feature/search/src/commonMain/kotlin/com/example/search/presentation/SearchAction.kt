package com.example.search.presentation

import com.example.search.domain.FilmDetails

sealed interface SearchAction {
    data object Init : SearchAction
    data object LoadMore : SearchAction
    data class QueryChanged(val query: String) : SearchAction
    data class FilmsLoaded(val films: List<FilmDetails>, val isLoadMore: Boolean = false) : SearchAction
    data class LoadError(val errorMessage: String) : SearchAction
}