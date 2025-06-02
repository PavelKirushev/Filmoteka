package com.example.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genres (

  @SerialName("genre" ) var genre : String? = null

)