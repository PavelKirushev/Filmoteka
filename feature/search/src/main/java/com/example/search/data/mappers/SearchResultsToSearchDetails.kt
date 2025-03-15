package com.example.search.data.mappers

import com.example.example.SearchResults
import com.example.search.data.SearchDetails

fun SearchResults.toSearchDetails(): SearchDetails {
    return SearchDetails(
        this.films,
        this.searchFilmsCountResult
    )
}