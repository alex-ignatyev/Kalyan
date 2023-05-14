package screens.main.tobacco_info

sealed class TobaccoInfoEvent {
    data class InitTobaccoInfoScreen(val tobaccoId: String) : TobaccoInfoEvent()
    class VoteForTobacco : TobaccoInfoEvent()
    class ClearActions : TobaccoInfoEvent()
}

data class TobaccoInfoState(
    val isLoading: Boolean = true,

    val id: String = "",
    val taste: String = "",
    val company: String = "",
    val line: String = "",

    var image: String = "",

    val strengthByCompany: Int = 0,

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

    val votes: Int = 0,

    val error: String = ""
)

sealed class TobaccoInfoAction
