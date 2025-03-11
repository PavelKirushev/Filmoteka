package com.example.film.data.mappers

import com.example.film.data.FilmDetails
import com.example.film.data.network.models.Film

fun Film.toFilmDetails() : FilmDetails {
    return FilmDetails(
        nameRu = this.nameRu,
        nameOriginal = this.nameOriginal,
        imageUrl = this.posterUrl,
        rating = this.ratingImdb,
        year = this.year,
        description = this.description,
        countries = this.countries,
        genres = this.genres,
    )
}