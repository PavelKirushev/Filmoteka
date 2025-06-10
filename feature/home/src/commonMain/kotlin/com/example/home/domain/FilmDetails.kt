package com.example.home.domain

data class FilmDetails(
    val id: Int?,
    val nameRu: String?,
    val nameEn: String?,
    val ratingKinopoisk: Double?,
    val ratingImdb: Double?,
    val year: Int?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
)
