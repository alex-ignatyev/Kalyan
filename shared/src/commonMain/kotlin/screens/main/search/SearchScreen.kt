package screens.main.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.adeo.kviewmodel.compose.ViewModel

object SearchScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        ViewModel(factory = { SearchViewModel() }) { viewModel ->
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
}
