package model.tobacco

import kotlinx.serialization.Serializable

@Serializable
data class TobaccoInfoResponse(
    val id: String,
    val taste: String,
    val company: String,
    val line: String = "",

    var image: String = "",

    val strengthByCompany: Int,

    val strengthByUsers: Float = 0f,
    val smokinessByUsers: Float = 0f,
    val aromaByUsers: Float = 0f,
    val ratingByUsers: Float = 0f,
    val tastePowerByUsers: Float = 0f,

    val strengthByUser: Int = 0,
    val smokinessByUser: Int = 0,
    val aromaByUser: Int = 0,
    val tastePowerByUser: Int = 0,
    val ratingByUser: Int = 0,

    val votes: Int
)
