package com.example.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResults (

  @SerialName("keyword"                ) var keyword                : String?          = null,
  @SerialName("pagesCount"             ) var pagesCount             : Int?             = null,
  @SerialName("films"                  ) var films                  : ArrayList<Films> = arrayListOf(),
  @SerialName("searchFilmsCountResult" ) var searchFilmsCountResult : Int?             = null

)