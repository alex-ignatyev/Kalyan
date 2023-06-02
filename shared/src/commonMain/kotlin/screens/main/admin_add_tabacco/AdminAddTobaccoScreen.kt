package screens.main.admin_add_tabacco

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.adeo.kviewmodel.compose.ViewModel
import screens.main.admin_add_tabacco.AdminAddTobaccoAction.OpenCompanySheet
import screens.main.admin_add_tabacco.AdminAddTobaccoAction.OpenLineSheet
import screens.main.admin_add_tabacco.AdminAddTobaccoAction.ReturnToPreviousScreen
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ClearActions
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.InitAdminAddTobaccoScreen

object AdminAddTobaccoScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        ViewModel(factory = { AdminAddTobaccoViewModel() }) { viewModel ->
            val state by viewModel.viewStates().collectAsState()
            val action by viewModel.viewActions().collectAsState(null)

            AdminAddTobaccoView(state) { event ->
                viewModel.obtainEvent(event)
            }

            LaunchedEffect(Unit) {
                viewModel.obtainEvent(InitAdminAddTobaccoScreen())
            }

            when (action) {
                is ReturnToPreviousScreen -> navigator.pop()
                is OpenCompanySheet -> {
                    bottomSheetNavigator.show(CompanyBottomSheet(state.companies, obtainEvent = viewModel::obtainEvent))
                    viewModel.obtainEvent(ClearActions())
                }

                is OpenLineSheet -> {
                    bottomSheetNavigator.show(LineBottomSheet((action as OpenLineSheet).lines, obtainEvent = viewModel::obtainEvent))
                    viewModel.obtainEvent(ClearActions())
                }

                null -> {}
            }
        }
    }
}
