package screens.old.settings

import com.adeo.kviewmodel.BaseSharedViewModel
import screens.old.settings.models.SettingsAction
import screens.old.settings.models.SettingsEvent
import screens.old.settings.models.SettingsViewState

class SettingsViewModel: BaseSharedViewModel<SettingsViewState, SettingsAction, SettingsEvent>(
    initialState = SettingsViewState()
) {

    override fun obtainEvent(viewEvent: SettingsEvent) {
        when (viewEvent) {
            else -> {}
        }
    }
}