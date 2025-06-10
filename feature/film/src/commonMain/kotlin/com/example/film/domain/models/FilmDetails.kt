package com.example.film.domain.models

import com.example.film.data.network.models.Countries
import com.example.film.data.network.models.FilmVideoItem
import com.example.film.data.network.models.Genres

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
    val videos: List<FilmVideoItem>
)
