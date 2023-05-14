package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import ru.alexgladkov.odyssey.compose.base.BottomBarNavigator
import ru.alexgladkov.odyssey.compose.extensions.customNavigation
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.helpers.FlowBuilder
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.MultiStackConfiguration
import ru.alexgladkov.odyssey.compose.navigation.tabs.TabDefaults
import screens.auth.account_create.AccountCreateScreen
import screens.auth.account_forgot.AccountForgotScreen
import screens.auth.account_login.AccountLoginScreen
import screens.main.admin_add_tabacco.AdminAddTobaccoScreen
import screens.main.profile.ProfileScreen
import screens.main.rating.RatingScreen
import screens.main.search.SearchScreen
import screens.main.settings.SettingsScreen
import screens.main.tobacco_info.TobaccoInfoScreen
import screens.splash.SplashScreen
import ui.KalyanTheme

@Composable
internal fun RootComposeBuilder.navigationGraph() {

    screen(SCREEN_SPLASH) {
        SplashScreen()
    }

    customNavigation(
        FLOW_MAIN,
        content = {
            val configuration = MultiStackConfiguration.BottomNavConfiguration(
                backgroundColor = KalyanTheme.colors.primaryBackground,
                defaultElevation = 8.dp
            )

            BottomBarNavigator(
                startScreen = null,
                bottomNavConfiguration = configuration
            )

        }
    ) {
        val colors = TabDefaults.simpleTabColors(
            selectedColor = KalyanTheme.colors.generalColor,
            unselectedColor = KalyanTheme.colors.secondaryText
        )

        tab(TabDefaults.content(AppRes.string.title_rating, Icons.Filled.Star), colors) {
            screen("rating") {
                RatingScreen()
            }

            screen("admin_add_tobacco") {
                AdminAddTobaccoScreen()
            }

            screen("tobacco_info") {
                TobaccoInfoScreen(it as String)
            }
        }

        tab(TabDefaults.content(AppRes.string.title_search, Icons.Filled.Search), colors) {
            screen("search") {
                SearchScreen()
            }
        }

        tab(TabDefaults.content(AppRes.string.title_profile, Icons.Filled.Person), colors) {
            profileFlow()
        }
    }

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

internal fun RootComposeBuilder.authFlow() {
    flow("login") {
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
