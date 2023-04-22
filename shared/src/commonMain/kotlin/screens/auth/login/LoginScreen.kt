package screens.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.auth.login.LoginAction.OpenSmsScreen
import screens.auth.login.LoginEvent.ClearActions

@Composable
internal fun LoginScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { LoginViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        LoginView(state) { event ->
            viewModel.obtainEvent(event)
        }

        when (action) {
            is OpenSmsScreen -> {
                rootController.push("get_sms")
                viewModel.obtainEvent(ClearActions())
            }
            null -> {}
        }
    }
}