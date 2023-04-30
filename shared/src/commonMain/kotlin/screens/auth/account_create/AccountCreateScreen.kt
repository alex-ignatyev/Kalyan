package screens.auth.account_create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import navigation.SCREEN_LOGIN
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.auth.account_create.AccountCreateAction.OpenLoginScreen
import screens.auth.account_create.AccountCreateAction.ReturnToPreviousScreen
import screens.auth.account_create.AccountCreateEvent.ClearActions

@Composable
internal fun AccountCreateScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { AccountCreateViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        AccountCreateView(state) { event ->
            viewModel.obtainEvent(event)
        }

        when (action) {
            is OpenLoginScreen -> {
                rootController.push(SCREEN_LOGIN)
                viewModel.obtainEvent(ClearActions())
            }

            is ReturnToPreviousScreen -> rootController.popBackStack()
            null -> {}
        }
    }
}
