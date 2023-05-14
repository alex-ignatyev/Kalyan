package screens.main.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.SCREEN_LOGIN
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
import screens.main.settings.SettingsAction.OpenLoginScreen
import screens.main.settings.SettingsAction.ReturnBack

@Composable
internal fun SettingsScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { SettingsViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        SettingsView(state) {
            viewModel.obtainEvent(it)
        }

        when (action) {
            is OpenLoginScreen -> rootController.push(SCREEN_LOGIN)
            is ReturnBack -> rootController.popBackStack()
            else -> {}
        }
    }
}
