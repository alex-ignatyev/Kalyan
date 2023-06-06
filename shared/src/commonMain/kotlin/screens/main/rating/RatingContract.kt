package screens.main.rating

import model.domain.TobaccoFeed

sealed class RatingEvent {
    class InitRatingScreen : RatingEvent()
    data class OnTobaccoClick(val tobaccoId: String) : RatingEvent()
    class AddTobaccoClick : RatingEvent()
    class ClearActions : RatingEvent()
}

data class RatingState(
    val isLoading: Boolean = true,
    val data: List<TobaccoFeed> = emptyList(),
    val isAdmin: Boolean = false
)

sealed class RatingAction {
    class OpenTobaccoInfoScreen(val tobaccoId: String) : RatingAction()
    class OpenAdminAddTobaccoScreen : RatingAction()
}
