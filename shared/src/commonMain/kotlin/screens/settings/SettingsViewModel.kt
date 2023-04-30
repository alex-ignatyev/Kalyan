package screens.settings

import com.adeo.kviewmodel.BaseSharedViewModel

class SettingsViewModel : BaseSharedViewModel<SettingsState, SettingsAction, SettingsEvent>(
    initialState = SettingsState()
) {

    override fun obtainEvent(viewEvent: SettingsEvent) {

    }
}
