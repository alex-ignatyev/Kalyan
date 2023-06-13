package screens.main.tobacco.tobacco_feed

import model.domain.TobaccoFeed

sealed class TobaccoFeedEvent {
    class InitTobaccoFeedScreen : TobaccoFeedEvent()
    data class OnTobaccoClick(val tobaccoId: String) : TobaccoFeedEvent()
    class AddTobaccoClick : TobaccoFeedEvent()
    class ClearActions : TobaccoFeedEvent()
}

data class TobaccoFeedState(
    val isLoading: Boolean = true,
    val data: List<TobaccoFeed> = emptyList(),
    val isAdmin: Boolean = false
)

sealed class TobaccoFeedAction {
    class OpenTobaccoInfoScreen(val tobaccoId: String) : TobaccoFeedAction()
    class OpenAdminAddTobaccoScreen : TobaccoFeedAction()
}
