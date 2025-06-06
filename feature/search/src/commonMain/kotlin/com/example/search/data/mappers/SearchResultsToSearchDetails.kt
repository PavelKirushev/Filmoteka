package com.example.search.data.mappers

import com.example.search.Countries
import com.example.search.Genres
import com.example.search.SearchResults
import com.example.search.data.network.models.Collections
import com.example.search.domain.FilmDetails
import com.example.search.domain.SearchDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun SearchResults.toSearchDetails(): SearchDetails {
    return SearchDetails(
        films = this.films.map {
            FilmDetails(
                filmId = it.filmId,
                nameRu = it.nameRu,
                type = it.type,
                year = it.year,
                description = it.description,
                countries = it.countries,
                genres = it.genres,
                rating = it.rating,
                ratingVoteCount = it.ratingVoteCount,
                posterUrl = it.posterUrl,
                posterUrlPreview = it.posterUrlPreview

            )
        },
        searchCount = searchFilmsCountResult
    )
}

fun Collections.toSearchDetails(): SearchDetails {
    return SearchDetails(
        films = this.items.map {
            FilmDetails(
                filmId = it.kinopoiskId,
                nameRu = it.nameRu,
                type = it.type,
                year = it.year.toString(),
                description = "",
                countries = it.countries,
                genres = it.genres,
                rating = it.ratingKinopoisk.toString(),
                ratingVoteCount = null,
                posterUrl = it.posterUrl,
                posterUrlPreview = it.posterUrlPreview
            )
        },
        searchCount = this.total
    )
}