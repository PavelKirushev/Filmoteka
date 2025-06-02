package com.example.search.data

import com.example.search.data.mappers.toSearchDetails
import com.example.search.domain.SearchDetails
import com.example.search.data.network.SearchApi
import com.example.search.domain.SearchRepository

class SearchRepositoryImpl(
    private val searchApi: SearchApi
): SearchRepository {
    override suspend fun getSearchDetails(keyword: String, page: Int): SearchDetails = searchApi.getSearchResult(keyword, page).toSearchDetails()
}