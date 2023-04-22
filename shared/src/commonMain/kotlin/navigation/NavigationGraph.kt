package navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import ru.alexgladkov.odyssey.compose.extensions.bottomNavigation
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.BottomBarDefaults
import ru.alexgladkov.odyssey.compose.navigation.tabs.TabDefaults
import screens.old.add_dates.MedicationAddDates
import screens.old.add_name.MedicationAddName
import screens.old.compose.ComposeScreen
import screens.old.daily.DailyScreen
import screens.old.daily.views.HabitCardItemModel
import screens.old.detail.DetailScreen
import screens.old.settings.SettingsScreen
import screens.splash.SplashScreen
import screens.old.stats.StatisticsScreen
import com.kalyan.shared.AppRes
import screens.auth.get_sms.GetSmsScreen
import screens.auth.login.LoginScreen
import screens.main.MainScreen
import ui.themes.KalyanTheme

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
internal fun RootComposeBuilder.navigationGraph() {
    screen("splash") {
        SplashScreen()
    }

    screen("login") {
        LoginScreen()
    }

    screen("get_sms") {
        GetSmsScreen()
    }

    bottomNavigation(
        "main",
        colors = BottomBarDefaults.bottomColors(
            backgroundColor = KalyanTheme.colors.primaryBackground
        )
    ) {
        val colors = TabDefaults.simpleTabColors(
            selectedColor = KalyanTheme.colors.primaryText,
            unselectedColor = KalyanTheme.colors.controlColor
        )

        tab(TabDefaults.content(AppRes.string.title_daily, Icons.Filled.DateRange), colors) {
            screen("daily") {
                MainScreen()
            }

            screen("detail") {
                DetailScreen(it as HabitCardItemModel)
            }
        }

        tab(TabDefaults.content(AppRes.string.title_statistics, Icons.Filled.Star), colors) {
            screen("statistics") {
                StatisticsScreen()
            }
        }

        tab(TabDefaults.content(AppRes.string.title_settings, Icons.Filled.Settings), colors) {
            screen("settings") {
                SettingsScreen()
            }
        }
    }

    screen("compose") {
        ComposeScreen()
    }

    medicationAddFlow()
}

internal fun RootComposeBuilder.medicationAddFlow() {
    flow("medication_add_flow") {
        screen("medication_name") {
            MedicationAddName()
        }

        screen("medication_add_dates") {
            MedicationAddDates(title = it as String)
        }
    }
}