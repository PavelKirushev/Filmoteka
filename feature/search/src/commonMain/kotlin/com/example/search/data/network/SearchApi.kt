package com.example.search.data.network

import com.example.search.SearchResults
import com.example.search.data.network.models.Collections
import com.example.search.domain.FilmDetails

interface SearchApi {
    suspend fun getFilms(page: Int): Collections
    suspend fun searchFilms(keyword: String, page: Int): SearchResults
}