package screens.old.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.old.stats.models.StatsEvent
import screens.old.stats.models.StatsViewState
import screens.old.stats.views.StatisticCell
import com.kalyan.shared.AppRes
import ui.themes.KalyanTheme

@Composable
internal fun StatisticsScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { StatisticsViewModel() }) { viewModel ->
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)

        StatisticsView(viewState)

        LaunchedEffect(Unit) {
            viewModel.obtainEvent(StatsEvent.ReloadScreen)
        }
    }
}

@Composable
internal fun StatisticsView(viewState: StatsViewState) {
    Column {
        Text(
            text = AppRes.string.title_statistics,
            style = KalyanTheme.typography.header,
            color = KalyanTheme.colors.primaryText
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            viewState.activeProgress.forEach {
                item {
                    StatisticCell(it)
                }
            }
        }
    }
}