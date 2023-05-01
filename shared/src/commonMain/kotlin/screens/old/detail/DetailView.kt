package screens.old.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import com.soywiz.klock.DateTime
import di.LocalPlatform
import di.Platform
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import screens.old.detail.models.DetailEvent
import screens.old.detail.models.DetailViewState
import ui.themes.KalyanTheme
import ui.themes.components.CCalendar
import ui.themes.components.JetMenu

@Composable
internal fun DetailView(
    viewState: DetailViewState,
    eventHandler: (DetailEvent) -> Unit
) {
    val platform = LocalPlatform.current
    val rootController = LocalRootController.current
    val modalController = rootController.findModalController()

    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            if (platform == Platform.iOS) {
                Icon(
                    modifier = Modifier
                        .clickable { eventHandler.invoke(DetailEvent.CloseScreen) }
                        .size(56.dp).padding(16.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = KalyanTheme.colors.controlColor
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = viewState.itemTitle,
                    style = KalyanTheme.typography.header,
                    color = KalyanTheme.colors.primaryText
                )

                Text(
                    text = if (viewState.isGood) "Good Habit" else "Bad Habit",
                    style = KalyanTheme.typography.caption,
                    color = KalyanTheme.colors.controlColor
                )
            }

            Icon(
                modifier = Modifier.clickable { eventHandler.invoke(DetailEvent.DeleteItem) }
                    .size(56.dp).padding(16.dp),
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete Item",
                tint = KalyanTheme.colors.controlColor)
        }

        Spacer(modifier = Modifier.height(24.dp))

        JetMenu(
            title = AppRes.string.title_start_date,
            value = viewState.startDate
        ) {
            val configuration = ModalSheetConfiguration(
                maxHeight = 0.6f,
                cornerRadius = 16,
            )
            modalController.present(configuration) { key ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(KalyanTheme.colors.primaryBackground)
                        .padding(16.dp)
                ) {
                    CCalendar(
                        selectedDate = viewState.start ?: DateTime.now(),
                        textColor = KalyanTheme.colors.primaryText,
                        dayOfWeekColor = KalyanTheme.colors.controlColor,
                        selectedColor = KalyanTheme.colors.tintColor
                    ) {
                        eventHandler.invoke(DetailEvent.StartDateSelected(it))
                        modalController.popBackStack(key)
                    }
                }
            }
        }

        JetMenu(
            title = AppRes.string.title_end_date,
            value = viewState.endDate
        ) {
            val configuration = ModalSheetConfiguration(
                maxHeight = 0.6f,
                cornerRadius = 16,
            )
            modalController.present(configuration) { key ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .background(KalyanTheme.colors.primaryBackground)
                        .padding(16.dp)
                ) {
                    CCalendar(
                        selectedDate = viewState.end ?: DateTime.now(),
                        textColor = KalyanTheme.colors.primaryText,
                        dayOfWeekColor = KalyanTheme.colors.controlColor,
                        selectedColor = KalyanTheme.colors.tintColor
                    ) {
                        eventHandler.invoke(DetailEvent.EndDateSelected(it))
                        modalController.popBackStack(key)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                .height(48.dp)
                .fillMaxWidth(),
            onClick = { eventHandler.invoke(DetailEvent.SaveChanges) },
            enabled = !viewState.isDeleting,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = KalyanTheme.colors.tintColor,
                disabledBackgroundColor = KalyanTheme.colors.tintColor.copy(
                    alpha = 0.3f
                )
            )
        ) {
            Text(
                text = AppRes.string.action_save,
                style = KalyanTheme.typography.body,
                color = Color.White
            )
        }
    }
}