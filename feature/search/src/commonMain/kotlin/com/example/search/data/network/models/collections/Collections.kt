package com.example.search.data.network.models.collections

import Item
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collections(
    @SerialName("total") val total: Int,
    @SerialName("totalPages") val totalPages: Long,
    @SerialName("items") val items: ArrayList<Item>,
)