package screens.main.tobacco_info

import model.tobacco.TobaccoVoteRequest.VoteType

sealed class TobaccoInfoEvent {
    data class InitTobaccoInfoScreen(val tobaccoId: String) : TobaccoInfoEvent()
    data class VoteForTobacco(val type: VoteType, val value: Long) : TobaccoInfoEvent()
    class OnBackClick : TobaccoInfoEvent()
    class ClearActions : TobaccoInfoEvent()
}

data class TobaccoInfoState(
    val isLoading: Boolean = true,

    var image: String = "",

    val taste: String = "",
    val company: String = "",
    val line: String = "",
    val strengthByCompany: Int = 0,

    val ratingByUsers: Float = 0f,
    val strengthByUsers: Float = 0f,
    val smokinessByUsers: Float = 0f,
    val aromaByUsers: Float = 0f,
    val tasteByUsers: Float = 0f,

    val ratingByUser: Long = 0,
    val strengthByUser: Long = 0,
    val smokinessByUser: Long = 0,
    val aromaByUser: Long = 0,
    val tasteByUser: Long = 0,

    val votes: Long = 0,

    val ratingRate: Int = 0,
    val strengthRate: Int = 0,
    val smokinessRate: Int = 0,
    val aromaRate: Int = 0,
    val tasteRate: Int = 0,

    val error: String = ""
)

sealed class TobaccoInfoAction {
    class ReturnBack : TobaccoInfoAction()
}
