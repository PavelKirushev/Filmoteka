package com.example.search.data.network

import com.example.search.SearchResults

interface SearchApi {
    suspend fun getSearchResult(keyword: String, page: Int): SearchResults
}