package screens.main

import model.Tobacco

sealed class MainEvent {
    class TestClick : MainEvent()
}

data class MainState(
    val tobaccos: List<Tobacco> = emptyList()
)

sealed class MainAction {

}
