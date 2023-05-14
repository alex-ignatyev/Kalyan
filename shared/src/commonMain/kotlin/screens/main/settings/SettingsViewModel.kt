package screens.main.settings

import com.adeo.kviewmodel.BaseSharedViewModel
import data.SettingsDataSource
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.settings.SettingsAction.OpenLoginScreen
import screens.main.settings.SettingsAction.ReturnBack
import screens.main.settings.SettingsEvent.OnBackClick
import screens.main.settings.SettingsEvent.OnLogOutClick

class SettingsViewModel : KoinComponent, BaseSharedViewModel<SettingsState, SettingsAction, SettingsEvent>(
    initialState = SettingsState()
) {

    private val settings: SettingsDataSource by inject()

    override fun obtainEvent(viewEvent: SettingsEvent) {
        when (viewEvent) {
            is OnLogOutClick -> logOut()
            is OnBackClick -> returnBack()
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            settings.clear()
            viewAction = OpenLoginScreen()
        }
    }

    private fun returnBack() {
        viewAction = ReturnBack()
    }
}
