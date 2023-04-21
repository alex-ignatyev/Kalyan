package screens.old.settings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.odyssey.StoredViewModel
import data.features.settings.LocalSettingsEventBus
import screens.old.daily.views.HabitCardItem
import screens.old.daily.views.HabitCardItemModel
import screens.old.settings.models.SettingsViewState
import screens.old.settings.views.MenuItem
import screens.old.settings.views.MenuItemModel
import com.kalyan.shared.AppRes
import ui.themes.*
import ui.themes.JetHabitCorners.Flat
import ui.themes.JetHabitCorners.Rounded
import ui.themes.JetHabitSize.Big
import ui.themes.JetHabitSize.Medium
import ui.themes.JetHabitSize.Small
import ui.themes.JetHabitStyle.Blue
import ui.themes.JetHabitStyle.Green
import ui.themes.JetHabitStyle.Orange
import ui.themes.JetHabitStyle.Purple
import ui.themes.JetHabitStyle.Red
import ui.themes.components.JHDivider

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
internal fun SettingsScreen() {
//    StoredViewModel(factory = { SettingsViewModel() }) { viewModel ->
//        val viewState by viewModel.viewStates().collectAsState()
//        val viewAction by viewModel.viewActions().collectAsState(null)
//
////        SettingsView(viewState)
//    }

    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.value

    Surface(
        color = JetHabitTheme.colors.primaryBackground,
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {
            TopAppBar(
                backgroundColor = JetHabitTheme.colors.primaryBackground,
                elevation = 8.dp
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = JetHabitTheme.shapes.padding),
                    text = AppRes.string.title_settings,
                    color = JetHabitTheme.colors.primaryText,
                    style = JetHabitTheme.typography.toolbar
                )
            }

            Row(
                modifier = Modifier.padding(JetHabitTheme.shapes.padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = AppRes.string.action_dark_theme_enable,
                    color = JetHabitTheme.colors.primaryText,
                    style = JetHabitTheme.typography.body
                )
                Checkbox(
                    checked = currentSettings.isDarkMode, onCheckedChange = {
                        settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = JetHabitTheme.colors.tintColor,
                        uncheckedColor = JetHabitTheme.colors.secondaryText
                    )
                )
            }

            Divider(
                modifier = Modifier.padding(start = JetHabitTheme.shapes.padding),
                thickness = 0.5.dp,
                color = JetHabitTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            MenuItem(
                model = MenuItemModel(
                    title = AppRes.string.title_font_size,
                    currentIndex = when (currentSettings.textSize) {
                        Small -> 0
                        Medium -> 1
                        Big -> 2
                    },
                    values = listOf(
                        AppRes.string.title_font_size_small,
                        AppRes.string.title_font_size_medium,
                        AppRes.string.title_font_size_big
                    )
                ),
                onItemSelected = {
                    settingsEventBus.updateTextSize(
                        when (it) {
                            0 -> Small
                            1 -> Medium
                            2 -> Big
                            else -> throw NotImplementedError("No valid value for this $it")
                        }
                    )
                }
            )

            MenuItem(
                model = MenuItemModel(
                    title = AppRes.string.title_padding_size,
                    currentIndex = when (currentSettings.paddingSize) {
                        Small -> 0
                        Medium -> 1
                        Big -> 2
                    },
                    values = listOf(
                        AppRes.string.title_padding_small,
                        AppRes.string.title_padding_medium,
                        AppRes.string.title_padding_big
                    )
                ),
                onItemSelected = {
                    settingsEventBus.updatePaddingSize(
                        when (it) {
                            0 -> Small
                            1 -> Medium
                            2 -> Big
                            else -> throw NotImplementedError("No valid value for this $it")
                        }
                    )
                }
            )

            MenuItem(
                model = MenuItemModel(
                    title = AppRes.string.title_corners_style,
                    currentIndex = when (currentSettings.cornerStyle) {
                        Rounded -> 0
                        Flat -> 1
                    },
                    values = listOf(
                        AppRes.string.title_corners_style_rounded,
                        AppRes.string.title_corners_style_flat
                    )
                ),
                onItemSelected = {
                    settingsEventBus.updateCornerStyle(
                        when (it) {
                            0 -> Rounded
                            1 -> Flat
                            else -> throw NotImplementedError("No valid value for this $it")
                        }
                    )
                }
            )

            Row(
                modifier = Modifier
                    .padding(JetHabitTheme.shapes.padding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ColorCard(color = if (currentSettings.isDarkMode) purpleDarkPalette.tintColor else purpleLightPalette.tintColor,
                    onClick = {
                        settingsEventBus.updateStyle(Purple)
                    })
                ColorCard(color = if (currentSettings.isDarkMode) orangeDarkPalette.tintColor else orangeLightPalette.tintColor,
                    onClick = {
                        settingsEventBus.updateStyle(Orange)
                    })
                ColorCard(color = if (currentSettings.isDarkMode) blueDarkPalette.tintColor else blueLightPalette.tintColor,
                    onClick = {
                        settingsEventBus.updateStyle(Blue)
                    })
            }

            Row(
                modifier = Modifier
                    .padding(JetHabitTheme.shapes.padding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ColorCard(color = if (currentSettings.isDarkMode) redDarkPalette.tintColor else redLightPalette.tintColor,
                    onClick = {
                        settingsEventBus.updateStyle(Red)
                    })
                ColorCard(color = if (currentSettings.isDarkMode) greenDarkPalette.tintColor else greenLightPalette.tintColor,
                    onClick = {
                        settingsEventBus.updateStyle(Green)
                    })
            }

            HabitCardItem(
                model = HabitCardItemModel(
                    habitId = 0,
                    title = AppRes.string.card_example,
                    isChecked = true,
                )
            )
        }
    }
}

@Composable
private fun SettingsView(viewState: SettingsViewState) {
    Column(modifier = Modifier.fillMaxSize().background(JetHabitTheme.colors.secondaryBackground)) {
        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.padding(20.dp)) {
            Card(
                modifier = Modifier.weight(1f).height(80.dp),
                backgroundColor = JetHabitTheme.colors.primaryBackground,
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp)
            ) {

            }

            Spacer(modifier = Modifier.width(20.dp))

            Card(
                modifier = Modifier.size(80.dp),
                backgroundColor = JetHabitTheme.colors.primaryBackground,
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "${viewState.healthPercentage}",
                        fontSize = 20.sp,
                        color = JetHabitTheme.colors.tintColor,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Card(
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            backgroundColor = JetHabitTheme.colors.primaryBackground,
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)) {
                    Text(
                        text = AppRes.string.settings_body_metrics,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = JetHabitTheme.colors.primaryText
                    )
                }

                JHDivider()

                Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)) {
                    Text(
                        text = AppRes.string.settings_theme,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = JetHabitTheme.colors.primaryText
                    )
                }

                JHDivider()
            }
        }
    }
}

@Composable
internal fun ColorCard(
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(60.dp)
            .clickable {
                onClick.invoke()
            },
        backgroundColor = color,
        elevation = 8.dp,
        shape = JetHabitTheme.shapes.cornersStyle
    ) { }
}