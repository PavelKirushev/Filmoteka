package com.example.film.data.mappers

import com.example.film.domain.models.FilmDetails
import com.example.film.data.network.models.Film
import com.example.film.data.network.models.FilmVideos

fun Film.toFilmDetails(
    videos: FilmVideos
) : FilmDetails {
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
        videos = videos.items
    )
}