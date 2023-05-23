package screens.main.tobacco_info

sealed class TobaccoInfoEvent {
    data class InitTobaccoInfoScreen(val tobaccoId: String) : TobaccoInfoEvent()
    data class ChangeStrengthSlider(val value: Float) : TobaccoInfoEvent()
    data class ChangeSmokinessSlider(val value: Float) : TobaccoInfoEvent()
    data class ChangeAromaSlider(val value: Float) : TobaccoInfoEvent()
    data class ChangeTasteSlider(val value: Float) : TobaccoInfoEvent()
    class VoteForTobacco : TobaccoInfoEvent()
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

    val votes: Long = 0,

    val strengthSlider: Float = 0f,
    val smokinessSlider: Float = 0f,
    val aromaSlider: Float = 0f,
    val tasteSlider: Float = 0f,

    val error: String = ""
)

sealed class TobaccoInfoAction {
    class OpenVoteBottomSheet : TobaccoInfoAction()
    class ReturnBack : TobaccoInfoAction()
}
