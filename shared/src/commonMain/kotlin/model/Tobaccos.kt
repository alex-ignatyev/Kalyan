package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tobaccos(
    @SerialName("tobaccos") val tobaccos: List<Tobacco>?
)

@Serializable
data class Tobacco(
    @SerialName("id") val id: String,
    @SerialName("company") val company: String,
    @SerialName("taste") val taste: String,
    @SerialName("strength") val strength: Int,
    @SerialName("smokiness") val smokiness: Int,
    @SerialName("aroma") val aroma: Int
)
