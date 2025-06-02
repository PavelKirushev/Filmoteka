package com.example.film.data.mappers

import com.example.film.domain.FilmDetails
import com.example.film.data.network.models.Film

fun Film.toFilmDetails() : FilmDetails {
    return FilmDetails(
        id = this.kinopoiskId,
        nameRu = this.nameRu,
        nameOriginal = this.nameOriginal,
        imageUrl = this.posterUrl,
        rating = this.ratingKinopoisk,
        year = this.year,
        description = this.description,
        countries = this.countries,
        genres = this.genres,
        ratingKinopoiskVoteCount = this.ratingKinopoiskVoteCount,
    )
}