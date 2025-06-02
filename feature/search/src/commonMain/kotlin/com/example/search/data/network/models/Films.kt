package com.example.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Films (

  @SerialName("filmId"           ) var filmId           : Int?                 = null,
  @SerialName("nameRu"           ) var nameRu           : String?              = null,
  @SerialName("type"             ) var type             : String?              = null,
  @SerialName("year"             ) var year             : String?              = null,
  @SerialName("description"      ) var description      : String?              = null,
  @SerialName("countries"        ) var countries        : ArrayList<Countries> = arrayListOf(),
  @SerialName("genres"           ) var genres           : ArrayList<Genres>    = arrayListOf(),
  @SerialName("rating"           ) var rating           : String?              = null,
  @SerialName("ratingVoteCount"  ) var ratingVoteCount  : Int?                 = null,
  @SerialName("posterUrl"        ) var posterUrl        : String?              = null,
  @SerialName("posterUrlPreview" ) var posterUrlPreview : String?              = null

)