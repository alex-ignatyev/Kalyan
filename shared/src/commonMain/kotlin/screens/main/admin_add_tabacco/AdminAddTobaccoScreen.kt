package screens.main.admin_add_tabacco

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.adeo.kviewmodel.compose.ViewModel
import screens.main.admin_add_tabacco.AdminAddTobaccoAction.ReturnToPreviousScreen
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.InitAdminAddTobaccoScreen

object AdminAddTobaccoScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        ViewModel(factory = { AdminAddTobaccoViewModel() }) { viewModel ->
            val state by viewModel.viewStates().collectAsState()
            val action by viewModel.viewActions().collectAsState(null)

            AdminAddTobaccoView(state) { event ->
                viewModel.obtainEvent(event)
            }

            viewModel.obtainEvent(InitAdminAddTobaccoScreen())

            when (action) {
                is ReturnToPreviousScreen -> navigator.pop()
                null -> {}
            }
        }
    }
}
