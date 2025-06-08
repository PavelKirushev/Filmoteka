package com.example.home.domain

data class CollectionsDetails(
    val total: Int?,
    val totalPages: Int?,
    val films: List<FilmDetails>
)