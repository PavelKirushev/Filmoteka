package com.example.home.domain

import com.example.example.Countries
import com.example.example.Genres

data class FilmDetails(
    val id: Int?,
    val nameRu: String?,
    val nameEn: String?,
    val ratingKinopoisk: Double?,
    val ratingImdb: Double?,
    val year: Int?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val coverUrl: String?,
    val logoUrl: String?,
)
