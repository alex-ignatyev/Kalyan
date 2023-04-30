package screens.main.rating

import model.Tobacco

sealed class RatingEvent {
    class TestCreate : RatingEvent()
}

data class RatingState(
    val tobaccos: List<Tobacco> = emptyList()
)

sealed class RatingAction
