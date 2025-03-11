package com.example.film.data

import com.example.film.data.network.models.Countries
import com.example.film.data.network.models.Genres

data class FilmDetails(
    val nameRu: String?,
    val nameOriginal: String?,
    val imageUrl: String?,
    val rating: Double?,
    val year: Int?,
    val description: String?,
    val countries: List<Countries>,
    val genres: List<Genres>,
)
