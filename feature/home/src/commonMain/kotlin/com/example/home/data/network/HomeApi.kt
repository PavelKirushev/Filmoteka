package com.example.home.data.network

import com.example.example.CollectionsResult

interface HomeApi {
    suspend fun getCollections(type: String, page: Int): CollectionsResult
}