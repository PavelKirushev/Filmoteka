package com.example.example

import com.google.gson.annotations.SerializedName


data class Films (

  @SerializedName("filmId"           ) var filmId           : Int?                 = null,
  @SerializedName("nameRu"           ) var nameRu           : String?              = null,
  @SerializedName("type"             ) var type             : String?              = null,
  @SerializedName("year"             ) var year             : String?              = null,
  @SerializedName("description"      ) var description      : String?              = null,
  @SerializedName("countries"        ) var countries        : ArrayList<Countries> = arrayListOf(),
  @SerializedName("genres"           ) var genres           : ArrayList<Genres>    = arrayListOf(),
  @SerializedName("rating"           ) var rating           : String?              = null,
  @SerializedName("ratingVoteCount"  ) var ratingVoteCount  : Int?                 = null,
  @SerializedName("posterUrl"        ) var posterUrl        : String?              = null,
  @SerializedName("posterUrlPreview" ) var posterUrlPreview : String?              = null

)