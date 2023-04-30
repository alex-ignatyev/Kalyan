package screens.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kalyan.shared.AppRes
import com.kalyan.shared.strings.AppResStrings
import data.features.settings.LocalSettingsEventBus
import ui.themes.KalyanTheme

@Composable
fun ProfileView(state: ProfileState, obtainEvent: (ProfileEvent) -> Unit) {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.value

    Surface(
        color = KalyanTheme.colors.primaryBackground,
    ) {
        Column(Modifier.fillMaxSize()) {
            TopAppBar(
                backgroundColor = KalyanTheme.colors.secondaryBackground,
                elevation = 8.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = AppResStrings.title_profile,
                        modifier = Modifier.weight(1f),
                        color = KalyanTheme.colors.primaryText,
                        style = KalyanTheme.typography.toolbar,
                    )
                    Image(
                        imageVector = Icons.Default.Settings, //TODO Добавить иконку
                        colorFilter = ColorFilter.tint(KalyanTheme.colors.primaryText),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            //TODO Добавить настройки
                        }
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

            Row(
                modifier = Modifier.padding(top = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Default.Person,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = null,
                    modifier = Modifier
                        .size(84.dp)
                        .clickable {

                        }
                )

                Spacer(modifier = Modifier.width(24.dp))

                Column {
                    Text(text = state.name, color = KalyanTheme.colors.secondaryText, fontSize = 20.sp)
                }
            }

            //TODO Добавить избранные табаки списком вправо с переходом на грид
            //TODO Добавить табаки которые хочет покурить списком вправо с переходом на грид
            //TODO Добавить достижения
        }
    }
}
