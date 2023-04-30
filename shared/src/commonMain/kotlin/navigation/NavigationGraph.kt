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
import ru.alexgladkov.odyssey.compose.helpers.FlowBuilder
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.BottomBarDefaults
import ru.alexgladkov.odyssey.compose.navigation.tabs.TabDefaults
import screens.auth.account_create.AccountCreateScreen
import screens.auth.account_forgot.AccountForgotScreen
import screens.auth.account_login.AccountLoginScreen
import screens.main.profile.ProfileScreen
import screens.main.rating.MainScreen
import screens.old.stats.StatisticsScreen
import screens.settings.SettingsScreen
import screens.splash.SplashScreen
import ui.themes.KalyanTheme

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
internal fun RootComposeBuilder.navigationGraph() {

    screen(SCREEN_SPLASH) {
        SplashScreen()
    }

    authFlow()

    bottomNavigation(
        FLOW_MAIN,
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

        tab(TabDefaults.content(AppRes.string.title_profile, Icons.Filled.Person), colors) {
            profileFlow()
        }
    }
}

internal fun RootComposeBuilder.authFlow() {
    flow(FLOW_AUTH) {
        screen(SCREEN_LOGIN) {
            AccountLoginScreen()
        }

        screen(SCREEN_CREATE) {
            AccountCreateScreen()
        }

        screen(SCREEN_FORGOT) {
            AccountForgotScreen()
        }
    }
}

internal fun FlowBuilder.profileFlow() {
    screen(SCREEN_PROFILE) {
        ProfileScreen()
    }

    screen(SCREEN_SETTINGS) {
        SettingsScreen()
    }
}
