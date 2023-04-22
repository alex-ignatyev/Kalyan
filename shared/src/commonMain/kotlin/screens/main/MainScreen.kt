package screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
internal fun MainScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { MainViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        MainView(state) { event ->
            viewModel.obtainEvent(event)
        }

        when (action) {
            else -> Unit
        }
    }
}
