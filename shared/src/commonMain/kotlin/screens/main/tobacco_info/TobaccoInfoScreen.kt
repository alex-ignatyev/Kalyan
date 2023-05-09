package screens.main.tobacco_info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.main.tobacco_info.TobaccoInfoEvent.InitTobaccoInfoScreen

@Composable
fun TobaccoInfoScreen(tobaccoId: String) {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { TobaccoInfoViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        TobaccoInfoView(state) {
            viewModel.obtainEvent(it)
        }

        viewModel.obtainEvent(InitTobaccoInfoScreen(tobaccoId))

        when (action) {
            else -> {}
        }
    }
}
