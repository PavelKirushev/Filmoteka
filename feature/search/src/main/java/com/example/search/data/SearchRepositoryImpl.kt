package com.example.search.data

import com.example.search.data.mappers.toSearchDetails
import com.example.search.data.modelsfordomain.SearchDetails
import com.example.search.data.network.SearchApi
import com.example.search.domain.SearchRepository

class SearchRepositoryImpl(
    private val searchApi: SearchApi
): SearchRepository {
    override suspend fun getSearchDetails(keyword: String?, page: Int): SearchDetails {
        val response = searchApi.getSearchResult(keyword, page, apiKey = "1c4b2b3b-2536-40a4-a5c8-00666a9418c3")
        return if (response.isSuccessful) {
            response.body()?.toSearchDetails() ?: throw Exception("Search error")
        } else {
            throw Exception("Search results are null")
        }
    }
}