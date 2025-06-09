package com.example.home.data.mappers

import com.example.example.CollectionsResult
import com.example.example.Items
import com.example.home.domain.CollectionsDetails
import com.example.home.domain.FilmDetails

fun CollectionsResult.toDomain(): CollectionsDetails {
    val films = this.items.map { it.toFilmDetails() }
    return CollectionsDetails(
        total = this.total,
        totalPages = this.totalPages,
        films = films
    )
}

fun Items.toFilmDetails(): FilmDetails {
    return FilmDetails(
        id = this.kinopoiskId,
        nameRu = this.nameRu,
        nameEn = this.nameEn,
        ratingKinopoisk = this.ratingKinopoisk,
        ratingImdb = this.ratingImdb,
        year = this.year,
        posterUrl = this.posterUrl,
        posterUrlPreview = this.posterUrlPreview,
    )
}