package com.example.search.data.network.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genres (

  @SerialName("genre" ) var genre : String? = null

)