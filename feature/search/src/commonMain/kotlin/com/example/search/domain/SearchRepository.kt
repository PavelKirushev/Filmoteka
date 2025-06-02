package com.example.search.domain

interface SearchRepository {
    suspend fun getSearchDetails(keyword: String, page: Int): SearchDetails
}