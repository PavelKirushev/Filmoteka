package com.example.search.data

import com.example.search.data.mappers.toSearchDetails
import com.example.search.data.network.SearchApi
import com.example.search.data.network.models.Collections
import com.example.search.domain.SearchDetails
import com.example.search.domain.SearchRepository

class SearchRepositoryImpl(
    private val searchApi: SearchApi
): SearchRepository {
    override suspend fun getFilms(page: Int): Collections {
        return searchApi.getFilms(page)
    }
    override suspend fun searchFilms(query: String, page: Int): SearchDetails {
        return searchApi.searchFilms(query, page).toSearchDetails()
    }
}