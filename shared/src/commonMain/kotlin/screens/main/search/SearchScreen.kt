package screens.main.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
internal fun SearchScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { SearchViewModel() }) { viewModel ->
        val state by viewModel.viewStates().collectAsState()
        val action by viewModel.viewActions().collectAsState(null)

        SearchView(state) {
            viewModel.obtainEvent(it)
        }

        when (action) {
            else -> {}
        }
    }
}
