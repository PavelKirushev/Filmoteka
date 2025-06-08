package com.example.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionsResult (

  @SerialName("total"      ) var total      : Int?             = null,
  @SerialName("totalPages" ) var totalPages : Int?             = null,
  @SerialName("items"      ) var items      : ArrayList<Items> = arrayListOf()

)