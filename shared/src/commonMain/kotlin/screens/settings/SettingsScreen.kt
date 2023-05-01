package screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.settings.SettingsAction.ReturnBack

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
            is ReturnBack -> rootController.popBackStack()
            else -> {}
        }
    }
}
