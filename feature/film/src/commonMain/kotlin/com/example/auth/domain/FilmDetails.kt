package com.example.auth.domain

import com.example.auth.data.network.models.Countries
import com.example.auth.data.network.models.Genres

data class FilmDetails(
    val id: Int?,
    val nameRu: String?,
    val nameOriginal: String?,
    val imageUrl: String?,
    val rating: Double?,
    val ratingKinopoiskVoteCount: Int?,
    val year: Int?,
    val description: String?,
    val countries: List<Countries>,
    val genres: List<Genres>,
)
