package screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import com.kalyan.shared.strings.AppResStrings
import data.features.settings.LocalSettingsEventBus
import screens.settings.SettingsEvent.OnBackClick
import ui.themes.KalyanTheme
import ui.themes.components.KalyanDivider
import ui.themes.components.KalyanToolbar

@Composable
fun SettingsView(state: SettingsState, obtainEvent: (SettingsEvent) -> Unit) {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.value

    Column(Modifier.fillMaxSize()) {
        KalyanToolbar(title = AppResStrings.title_settings, onBackClick = {
            obtainEvent.invoke(OnBackClick())
        })

        Row(
            modifier = Modifier.padding(16.dp),
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

        KalyanDivider()

        //TODO Добавить смену пароля
    }
}
