package model.tobacco

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TobaccoInfoRequest(
    @SerialName("userId") val userId: String,
    @SerialName("tobaccoId")  val tobaccoId: String
)
