package com.example.search.presentation

import com.example.search.domain.FilmDetails

data class SearchState (
    val query: String = "",
    val items: List<FilmDetails> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = true,
    val isLoadingMore: Boolean = false,
    var page: Int = 1,
    val canLoadMore: Boolean = true
)