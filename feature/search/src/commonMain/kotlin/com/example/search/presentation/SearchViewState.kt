package com.example.search.presentation

import com.example.search.domain.FilmDetails

sealed class SearchViewState {
    data object Loading : SearchViewState()
    data class Error(val errorText: String?) : SearchViewState()
    data class List(
        val items: kotlin.collections.List<FilmDetails>,
        val isLoadingMore: Boolean = false,
        val canLoadMore: Boolean = true,
        val query: String = ""
    ) : SearchViewState()
}