package screens.main.rating

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.main.rating.RatingAction.OpenTobaccoInfoScreen
import screens.main.rating.RatingEvent.ClearActions
import screens.main.rating.RatingEvent.InitRatingScreen

@Composable
internal fun RatingScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { RatingViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        RatingView(state) { event ->
            viewModel.obtainEvent(event)
        }

        viewModel.obtainEvent(InitRatingScreen())

        when (action) {
            is OpenTobaccoInfoScreen -> {
                rootController.push("tobacco_info", (action as OpenTobaccoInfoScreen).tobaccoId)
                viewModel.obtainEvent(ClearActions())
            }

            else -> Unit
        }
    }
}
