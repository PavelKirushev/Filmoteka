package com.example.search.domain

interface SearchRepository {
    suspend fun getFilms(page: Int): SearchDetails
    suspend fun searchFilms(query: String, page: Int): SearchDetails
}