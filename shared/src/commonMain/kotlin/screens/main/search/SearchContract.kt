package screens.main.search

sealed class SearchEvent {
    class InitSearchScreen : SearchEvent()
}

data class SearchState(
    val isLoading: Boolean = true
)

sealed class SearchAction
