package screens.main.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController

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
            else -> {}
        }
    }
}
