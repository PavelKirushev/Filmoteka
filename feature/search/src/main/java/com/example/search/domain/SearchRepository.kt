package com.example.search.domain

import com.example.search.data.modelsfordomain.SearchDetails

interface SearchRepository {
    suspend fun getSearchDetails(keyword: String?, page: Int): SearchDetails
}