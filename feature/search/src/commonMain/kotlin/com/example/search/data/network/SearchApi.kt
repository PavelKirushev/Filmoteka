package com.example.search.data.network

import com.example.search.data.network.models.collections.Collections
import com.example.search.data.network.models.films.SearchResults

interface SearchApi {
    suspend fun getFilms(page: Int): Collections
    suspend fun searchFilms(keyword: String, page: Int): SearchResults
}