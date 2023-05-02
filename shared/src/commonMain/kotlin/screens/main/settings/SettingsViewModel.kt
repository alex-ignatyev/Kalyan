package screens.main.settings

import com.adeo.kviewmodel.BaseSharedViewModel
import screens.main.settings.SettingsAction.ReturnBack
import screens.main.settings.SettingsEvent.OnBackClick

class SettingsViewModel : BaseSharedViewModel<SettingsState, SettingsAction, SettingsEvent>(
    initialState = SettingsState()
) {

    override fun obtainEvent(viewEvent: SettingsEvent) {
        when (viewEvent) {
            is OnBackClick -> returnBack()
        }
    }

    private fun returnBack() {
        viewAction = ReturnBack()
    }
}
