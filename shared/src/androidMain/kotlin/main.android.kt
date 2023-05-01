import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import data.features.settings.LocalSettingsEventBus
import data.features.settings.SettingsEventBus
import di.LocalPlatform
import di.Platform
import navigation.navigationGraph
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import ui.themes.KalyanTheme
import ui.themes.MainTheme

@Composable
fun MainView(activity: ComponentActivity) {
    val settingsEventBus = remember { SettingsEventBus() }
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    MainTheme(
        darkTheme = currentSettings.isDarkMode
    ) {
        val odysseyConfiguration = OdysseyConfiguration(
            canvas = activity,
            backgroundColor = KalyanTheme.colors.primaryBackground
        )

        CompositionLocalProvider(
            LocalPlatform provides Platform.Android,
            LocalSettingsEventBus provides settingsEventBus
        ) {
            setNavigationContent(odysseyConfiguration, onApplicationFinish = {
                activity.finishAffinity()
            }) {
                navigationGraph()
            }
        }
    }
}
