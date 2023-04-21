package screens.old.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.AlertConfiguration
import screens.old.daily.views.HabitCardItemModel
import screens.old.detail.models.DetailAction
import screens.old.detail.models.DetailEvent
import com.kalyan.shared.AppRes
import ui.themes.KalyanTheme

@Composable
internal fun DetailScreen(cardModel: HabitCardItemModel) {
    val rootController = LocalRootController.current
    val modalController = rootController.findModalController()

    StoredViewModel(factory = { DetailViewModel(cardModel) }) { viewModel ->
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)

        DetailView(viewState, eventHandler = { viewModel.obtainEvent(it) })

        when (viewAction) {
            DetailAction.CloseScreen -> rootController.popBackStack()
            DetailAction.DateError -> {
                val configuration = AlertConfiguration(
                    cornerRadius = 4, maxHeight = 0.4f,
                    maxWidth = 0.7f, alpha = 0.5f
                )
                modalController.present(alertConfiguration = configuration) { key ->
                    Column(
                        modifier = Modifier
                            .clickable { modalController.popBackStack(key) }
                            .fillMaxSize()
                            .background(KalyanTheme.colors.primaryBackground)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = AppRes.string.title_error,
                            color = KalyanTheme.colors.primaryText,
                            style = KalyanTheme.typography.heading
                        )

                        Text(
                            modifier = Modifier.padding(top = 24.dp),
                            text = AppRes.string.error_date,
                            color = KalyanTheme.colors.primaryText,
                            style = KalyanTheme.typography.body
                        )
                    }
                }

                viewModel.obtainEvent(DetailEvent.ActionInvoked)
            }

            null -> {}
        }
    }
}