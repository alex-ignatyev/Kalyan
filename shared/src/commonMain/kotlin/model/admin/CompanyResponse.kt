package model.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyResponse(
    @SerialName("id") val id: String,
    @SerialName("companyName") val companyName: String,
    @SerialName("lines") val lines: List<String>
)
