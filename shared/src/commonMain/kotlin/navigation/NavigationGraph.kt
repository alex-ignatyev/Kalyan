package navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.kalyan.shared.AppRes
import ru.alexgladkov.odyssey.compose.extensions.bottomNavigation
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.BottomBarDefaults
import ru.alexgladkov.odyssey.compose.navigation.tabs.TabDefaults
import screens.auth.account_create.AccountCreateScreen
import screens.auth.account_forgot.AccountForgotScreen
import screens.auth.account_login.AccountLoginScreen
import screens.main.MainScreen
import screens.old.add_dates.MedicationAddDates
import screens.old.add_name.MedicationAddName
import screens.old.compose.ComposeScreen
import screens.old.settings.SettingsScreen
import screens.old.stats.StatisticsScreen
import screens.splash.SplashScreen
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

    //TODO Переделать на authFlow
    screen("account_create") {
        AccountCreateScreen()
    }

    screen("account_login") {
        AccountLoginScreen()
    }

    screen("account_forgot") {
        AccountForgotScreen()
    }

    bottomNavigation(
        "main",
        colors = BottomBarDefaults.bottomColors(
            backgroundColor = KalyanTheme.colors.generalColor
        )
    ) {
        val colors = TabDefaults.simpleTabColors(
            selectedColor = KalyanTheme.colors.primaryText,
            unselectedColor = KalyanTheme.colors.secondaryText
        )

        tab(TabDefaults.content(AppRes.string.title_rating, Icons.Filled.Star), colors) {
            screen("rating") {
                MainScreen()
            }

            /*screen("detail") {
                DetailScreen(it as HabitCardItemModel)
            }*/
        }

        tab(TabDefaults.content(AppRes.string.title_search, Icons.Filled.Search), colors) {
            screen("search") {
                StatisticsScreen()
            }
        }

        // Избранное
        /*tab(TabDefaults.content(AppRes.string.title_search, Icons.Filled.Search), colors) {
            screen("search") {
                StatisticsScreen()
            }
        }*/

        // Хочу покурить / Покурил

        tab(TabDefaults.content(AppRes.string.title_profile, Icons.Filled.Person), colors) {
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