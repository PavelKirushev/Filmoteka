package com.example.search.data.network.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Countries (

  @SerialName("country" ) var country : String? = null

)