package com.example.home.domain

interface HomeRepository {
    suspend fun getCollections(type: String, page: Int): CollectionsDetails
}