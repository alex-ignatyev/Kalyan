package screens.main.rating

import model.tobacco.TobaccoResponse

sealed class RatingEvent {
    class InitRatingScreen : RatingEvent()
}

data class RatingState(
    val data: List<TobaccoResponse> = emptyList()
)

sealed class RatingAction
