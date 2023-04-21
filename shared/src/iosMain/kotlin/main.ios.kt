import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import data.features.settings.LocalSettingsEventBus
import data.features.settings.SettingsEventBus
import di.LocalPlatform
import di.Platform
import navigation.navigationGraph
import platform.UIKit.UIViewController
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import ui.themes.KalyanTheme
import ui.themes.MainTheme

fun MainViewController(): UIViewController = ComposeUIViewController {
    val settingsEventBus = remember { SettingsEventBus() }
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    MainTheme(
        style = currentSettings.style,
        darkTheme = currentSettings.isDarkMode,
        corners = currentSettings.cornerStyle,
        textSize = currentSettings.textSize,
        paddingSize = currentSettings.paddingSize
    ) {
        val odysseyConfiguration = OdysseyConfiguration(
            backgroundColor = KalyanTheme.colors.primaryBackground
        )

        val backgroundColor = KalyanTheme.colors.primaryBackground

        CompositionLocalProvider(
            LocalPlatform provides Platform.iOS,
            LocalSettingsEventBus provides settingsEventBus
        ) {
            Column {
                Box(modifier = Modifier.fillMaxWidth().height(30.dp).background(backgroundColor))
                setNavigationContent(odysseyConfiguration) {
                    navigationGraph()
                }
                Box(modifier = Modifier.fillMaxWidth().height(30.dp).background(backgroundColor))
            }
        }
    }
}
