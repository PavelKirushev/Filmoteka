package com.example.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Items (

  @SerialName("kinopoiskId"      ) var kinopoiskId      : Int?                 = null,
  @SerialName("imdbId"           ) var imdbId           : String?              = null,
  @SerialName("nameRu"           ) var nameRu           : String?              = null,
  @SerialName("nameEn"           ) var nameEn           : String?              = null,
  @SerialName("nameOriginal"     ) var nameOriginal     : String?              = null,
  @SerialName("countries"        ) var countries        : ArrayList<Countries> = arrayListOf(),
  @SerialName("genres"           ) var genres           : ArrayList<Genres>    = arrayListOf(),
  @SerialName("ratingKinopoisk"  ) var ratingKinopoisk  : Double?              = null,
  @SerialName("ratingImdb"       ) var ratingImdb       : Double?              = null,
  @SerialName("year"             ) var year             : Int?                 = null,
  @SerialName("type"             ) var type             : String?              = null,
  @SerialName("posterUrl"        ) var posterUrl        : String?              = null,
  @SerialName("posterUrlPreview" ) var posterUrlPreview : String?              = null,
  @SerialName("coverUrl"         ) var coverUrl         : String?              = null,
  @SerialName("logoUrl"          ) var logoUrl          : String?              = null,
  @SerialName("description"      ) var description      : String?              = null,
  @SerialName("ratingAgeLimits"  ) var ratingAgeLimits  : String?              = null

)