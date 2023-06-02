package screens.main.rating

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.adeo.kviewmodel.compose.ViewModel
import screens.main.admin_add_tabacco.AdminAddTobaccoScreen
import screens.main.rating.RatingAction.OpenAdminAddTobaccoScreen
import screens.main.rating.RatingAction.OpenTobaccoInfoScreen
import screens.main.rating.RatingEvent.ClearActions
import screens.main.rating.RatingEvent.InitRatingScreen
import screens.main.tobacco_info.TobaccoInfoScreen

object RatingScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        ViewModel(factory = { RatingViewModel() }) { viewModel ->
            val state by viewModel.viewStates().collectAsState()
            val action by viewModel.viewActions().collectAsState(null)

            RatingView(state) { event ->
                viewModel.obtainEvent(event)
            }

            LaunchedEffect(Unit) {
                viewModel.obtainEvent(InitRatingScreen())
            }

            when (action) {
                is OpenTobaccoInfoScreen -> {
                    navigator.push(TobaccoInfoScreen((action as OpenTobaccoInfoScreen).tobaccoId))
                    viewModel.obtainEvent(ClearActions())
                }

                is OpenAdminAddTobaccoScreen -> {
                    navigator.push(AdminAddTobaccoScreen)
                    viewModel.obtainEvent(ClearActions())
                }

                else -> Unit
            }
        }
    }
}
