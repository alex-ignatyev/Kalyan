import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import data.features.settings.LocalSettingsEventBus
import data.features.settings.SettingsEventBus
import di.LocalPlatform
import di.Platform.iOS
import navigation.navigationGraph
import platform.UIKit.UIViewController
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import ui.KalyanTheme
import ui.MainTheme

fun MainViewController(): UIViewController = ComposeUIViewController {
    val settingsEventBus = remember { SettingsEventBus() }
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    MainTheme(
        darkTheme = currentSettings.isDarkMode
    ) {
        val odysseyConfiguration = OdysseyConfiguration(
            backgroundColor = KalyanTheme.colors.primaryBackground
        )

        CompositionLocalProvider(
            LocalPlatform provides iOS,
            LocalSettingsEventBus provides settingsEventBus
        ) {
            setNavigationContent(odysseyConfiguration) {
                navigationGraph()
            }
        }
    }
}
