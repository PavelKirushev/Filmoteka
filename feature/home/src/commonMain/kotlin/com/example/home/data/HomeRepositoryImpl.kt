package com.example.home.data

import com.example.home.data.mappers.toDomain
import com.example.home.data.network.HomeApi
import com.example.home.domain.CollectionsDetails
import com.example.home.domain.HomeRepository

class HomeRepositoryImpl(private val api: HomeApi) : HomeRepository {

    override suspend fun getCollections(type: String, page: Int): CollectionsDetails {
        return api.getCollections(type, page).toDomain()
    }
}