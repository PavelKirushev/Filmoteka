package com.example.film.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class FilmVideoItem(
    val url: String,
    val name: String,
    val site: String,
)
