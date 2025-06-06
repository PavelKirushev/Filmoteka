package com.example.search.domain

import com.example.search.data.network.models.Collections

interface SearchRepository {
    suspend fun getFilms(page: Int): Collections
    suspend fun searchFilms(query: String, page: Int): SearchDetails
}