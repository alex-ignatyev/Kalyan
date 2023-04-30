package screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import com.kalyan.shared.strings.AppResStrings
import data.features.settings.LocalSettingsEventBus
import ui.themes.KalyanTheme

@Composable
fun SettingsView(state: SettingsState, obtainEvent: (SettingsEvent) -> Unit) {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.value

    Surface(color = KalyanTheme.colors.primaryBackground) {
        Column(Modifier.fillMaxSize()) {
            TopAppBar(
                backgroundColor = KalyanTheme.colors.secondaryBackground,
                elevation = 8.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //TODO Добавить стрелочку назад

                    Text(
                        text = AppResStrings.title_profile,
                        modifier = Modifier.weight(1f),
                        color = KalyanTheme.colors.primaryText,
                        style = KalyanTheme.typography.toolbar,
                    )
                }
            }

            Row(
                modifier = Modifier.padding(KalyanTheme.shapes.padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = AppRes.string.action_dark_theme_enable,
                    color = KalyanTheme.colors.primaryText,
                    style = KalyanTheme.typography.body
                )
                Checkbox(
                    checked = currentSettings.isDarkMode, onCheckedChange = {
                        settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = KalyanTheme.colors.tintColor,
                        uncheckedColor = KalyanTheme.colors.secondaryText
                    )
                )
            }

            Divider(
                modifier = Modifier.padding(start = KalyanTheme.shapes.padding),
                thickness = 0.5.dp,
                color = KalyanTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            //TODO Добавить смену пароля
        }
    }
}
