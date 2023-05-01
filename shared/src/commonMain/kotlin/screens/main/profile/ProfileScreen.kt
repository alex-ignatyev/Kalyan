package screens.main.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.SCREEN_SETTINGS
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.main.profile.ProfileAction.OpenSettingsScreen
import screens.main.profile.ProfileEvent.ClearActions

@Composable
internal fun ProfileScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { ProfileViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        ProfileView(state) {
            viewModel.obtainEvent(it)
        }

        when (action) {
            is OpenSettingsScreen -> {
                rootController.push(SCREEN_SETTINGS)
                viewModel.obtainEvent(ClearActions())
            }

            else -> {}
        }
    }
}
