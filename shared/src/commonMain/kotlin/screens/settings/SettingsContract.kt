package screens.settings

sealed class SettingsEvent

data class SettingsState(
    val isLoading: Boolean = true
)

sealed class SettingsAction
