package com.example.example

import com.google.gson.annotations.SerializedName


data class SearchResults (

  @SerializedName("keyword"                ) var keyword                : String?          = null,
  @SerializedName("pagesCount"             ) var pagesCount             : Int?             = null,
  @SerializedName("films"                  ) var films                  : ArrayList<Films> = arrayListOf(),
  @SerializedName("searchFilmsCountResult" ) var searchFilmsCountResult : Int?             = null

)