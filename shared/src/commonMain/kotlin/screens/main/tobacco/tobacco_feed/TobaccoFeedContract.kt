package screens.main.tobacco.tobacco_feed

import model.domain.TobaccoFeed
import utils.EMPTY
import utils.mvi.Action
import utils.mvi.Event

sealed class TobaccoFeedEvent : Event {
    class InitTobaccoFeedScreen : TobaccoFeedEvent()
    data class OnTobaccoSearch(val search: String) : TobaccoFeedEvent()
    class RefreshTobaccoFeedScreen : TobaccoFeedEvent()
    data class OnTobaccoClick(val tobaccoId: String) : TobaccoFeedEvent()
    class ClearActions : TobaccoFeedEvent()
}

data class TobaccoFeedState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isRefresh: Boolean = false,
    val search: String = EMPTY,
    val data: List<TobaccoFeed> = emptyList()
)

sealed class TobaccoFeedAction : Action {
    class OpenTobaccoInfoScreen(val tobaccoId: String) : TobaccoFeedAction()
}
