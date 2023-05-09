package screens.main.tobacco_info

sealed class TobaccoInfoEvent {
    data class InitTobaccoInfoScreen(val tobaccoId: String) : TobaccoInfoEvent()
    class VoteForTobacco : TobaccoInfoEvent()
    class ClearActions : TobaccoInfoEvent()
}

data class TobaccoInfoState(
    val isLoading: Boolean = true,
    val data: String = "",
    val error: String = ""
)

sealed class TobaccoInfoAction
