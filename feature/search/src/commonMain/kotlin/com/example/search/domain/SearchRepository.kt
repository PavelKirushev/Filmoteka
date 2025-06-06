package com.example.search.domain

import com.example.search.data.network.models.Collections

interface SearchRepository {
    suspend fun getFilms(page: Int): SearchDetails
    suspend fun searchFilms(query: String, page: Int): SearchDetails
}