package com.example.search.domain

data class SearchDetails(
    val films: List<FilmDetails>,
    val searchCount: Int?
)