package screens.settings

sealed class SettingsEvent {
    class OnBackClick : SettingsEvent()
}

data class SettingsState(
    val isLoading: Boolean = true
)

sealed class SettingsAction {
    class ReturnBack : SettingsAction()
}
