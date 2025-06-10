package com.example.film.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class FilmVideos(
    val total: Long,
    val items: List<FilmVideoItem>,
)
