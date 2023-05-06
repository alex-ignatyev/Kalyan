import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import data.LocalSettingsEventBus
import data.SettingsEventBus
import di.LocalPlatform
import di.Platform.Android
import navigation.navigationGraph
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import ui.KalyanTheme
import ui.MainTheme

@Composable
fun MainView(activity: ComponentActivity) {
    val systemUiController = rememberSystemUiController()
    val settingsEventBus = remember { SettingsEventBus() }
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    MainTheme(
        darkTheme = currentSettings.isDarkMode
    ) {
        systemUiController.setSystemBarsColor(
            color = KalyanTheme.colors.primaryBackground
        )

        val odysseyConfiguration = OdysseyConfiguration(
            canvas = activity,
            backgroundColor = KalyanTheme.colors.primaryBackground
        )

        CompositionLocalProvider(
            LocalPlatform provides Android,
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
