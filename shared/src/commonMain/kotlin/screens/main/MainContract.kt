package screens.main

import model.Tobacco

sealed class MainEvent {
    class TestCreate: MainEvent()
}

data class MainState(
    val tobaccos: List<Tobacco> = emptyList()
)

sealed class MainAction {

}
