package screens.auth.account_login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.auth.account_login.AccountLoginAction.OpenCreateAccountScreen
import screens.auth.account_login.AccountLoginAction.OpenForgotPasswordScreen
import screens.auth.account_login.AccountLoginAction.OpenMainScreen
import screens.auth.account_login.AccountLoginEvent.ClearActions

@Composable
internal fun AccountLoginScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { AccountLoginViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        AccountLoginView(state) { event ->
            viewModel.obtainEvent(event)
        }

        when (action) {
            is OpenMainScreen -> {
                rootController.push("main")
                viewModel.obtainEvent(ClearActions())
            }

            is OpenCreateAccountScreen -> {
                rootController.push("account_create")
                viewModel.obtainEvent(ClearActions())
            }

            is OpenForgotPasswordScreen -> {
                rootController.push("account_forgot")
                viewModel.obtainEvent(ClearActions())
            }

            null -> {}
        }
    }
}
