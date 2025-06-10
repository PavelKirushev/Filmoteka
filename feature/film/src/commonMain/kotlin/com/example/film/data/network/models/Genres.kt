package com.example.film.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genres (

  @SerialName("genre" ) var genre : String? = null

)