import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import data.LocalSettingsEventBus
import data.SettingsEventBus
import di.LocalPlatform
import di.Platform.Android
import di.Platform.iOS
import navigation.navigationGraph
import platform.UIKit.UIViewController
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import screens.splash.SplashScreen
import ui.KalyanTheme
import ui.MainTheme

fun MainViewController(): UIViewController = ComposeUIViewController {
    val settingsEventBus = remember { SettingsEventBus() }
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    MainTheme(
        darkTheme = currentSettings.isDarkMode
    ) {

        CompositionLocalProvider(
            LocalPlatform provides Android,
            LocalSettingsEventBus provides settingsEventBus
        ) {
            Navigator(SplashScreen)
        }

     /*   val odysseyConfiguration = OdysseyConfiguration(
            backgroundColor = KalyanTheme.colors.primaryBackground
        )

        CompositionLocalProvider(
            LocalPlatform provides iOS,
            LocalSettingsEventBus provides settingsEventBus
        ) {
            setNavigationContent(odysseyConfiguration) {
                navigationGraph()
            }
        }*/
    }
}
