package com.example.search.data.network

import com.example.search.data.network.models.films.SearchResults
import com.example.search.data.network.models.collections.Collections

interface SearchApi {
    suspend fun getFilms(page: Int): Collections
    suspend fun searchFilms(keyword: String, page: Int): SearchResults
}