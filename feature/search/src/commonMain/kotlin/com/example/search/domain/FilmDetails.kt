package com.example.search.domain

import com.example.search.data.network.models.common.Countries
import com.example.search.data.network.models.common.Genres

data class FilmDetails(
    val filmId: Int?,
    val nameRu: String?,
    val type: String?,
    val year: String?,
    val description: String?,
    val countries: ArrayList<Countries>,
    val genres: ArrayList<Genres>,
    val rating: String?,
    val ratingVoteCount: Int?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
)