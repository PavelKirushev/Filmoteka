package com.example.search.data.network.models

import com.example.search.Countries
import com.example.search.Genres
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collections(
    @SerialName("total") val total: Long,
    @SerialName("totalPages") val totalPages: Long,
    @SerialName("items") val items: List<Item>,
)

@Serializable
data class Item(
    @SerialName("kinopoiskId") val kinopoiskId: Long,
    @SerialName("nameRu") val nameRu: String,
    @SerialName("nameEn") val nameEn: String,
    @SerialName("nameOriginal") val nameOriginal: String,
    @SerialName("countries") val countries: List<Countries>,
    @SerialName("genres") val genres: List<Genres>,
    @SerialName("ratingKinopoisk") val ratingKinopoisk: Double,
    @SerialName("ratingImbd") val ratingImbd: Double,
    @SerialName("year") val year: String,
    @SerialName("type") val type: String,
    @SerialName("posterUrl") val posterUrl: String,
    @SerialName("posterUrlPreview") val posterUrlPreview: String,
)