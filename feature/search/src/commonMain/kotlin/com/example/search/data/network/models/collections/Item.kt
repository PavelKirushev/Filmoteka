import com.example.search.data.network.models.common.Countries
import com.example.search.data.network.models.common.Genres
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("kinopoiskId") val kinopoiskId: Int?,
    @SerialName("nameRu") val nameRu: String?,
    @SerialName("nameEn") val nameEn: String?,
    @SerialName("nameOriginal") val nameOriginal: String?,
    @SerialName("countries") val countries: ArrayList<Countries> = arrayListOf(),
    @SerialName("genres") val genres: ArrayList<Genres> = arrayListOf(),
    @SerialName("ratingKinopoisk") val ratingKinopoisk: Double?,
    @SerialName("ratingImbd") val ratingImbd: Double? = null,
    @SerialName("year") val year: Int?,
    @SerialName("type") val type: String?,
    @SerialName("posterUrl") val posterUrl: String?,
    @SerialName("posterUrlPreview") val posterUrlPreview: String?,
)