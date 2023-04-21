package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SmsRequest(
    @SerialName("code") val code: String
)
