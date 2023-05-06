package screens.main.admin_add_tabacco

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.main.admin_add_tabacco.AdminAddTobaccoAction.ReturnToPreviousScreen
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.InitAdminAddTobaccoScreen

@Composable
internal fun AdminAddTobaccoScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { AdminAddTobaccoViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        AdminAddTobaccoView(state) { event ->
            viewModel.obtainEvent(event)
        }

        viewModel.obtainEvent(InitAdminAddTobaccoScreen())

        when (action) {
            is ReturnToPreviousScreen -> rootController.popBackStack()
            null -> {}
        }
    }
}
