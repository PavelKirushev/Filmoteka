package com.example.search.data.network

import com.example.example.SearchResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchApi {
    @GET("api/v2.1/films/search-by-keyword")
    suspend fun getSearchResult(
        @Query("keyword") keyword: String?,
        @Query("page") page: Int = 1,
        @Header("X-API-KEY") apiKey: String
    ): Response<SearchResults>
}
