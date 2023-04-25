package screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import di.LocalPlatform
import kotlinx.coroutines.delay
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
import ui.themes.KalyanTheme

@Composable
internal fun SplashScreen() {
    val rootController = LocalRootController.current
    val platform = LocalPlatform.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KalyanTheme.colors.primaryBackground)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Kalyan",
                style = KalyanTheme.typography.header,
                color = KalyanTheme.colors.primaryText,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                text = "Full Compose Demo",
                style = KalyanTheme.typography.body,
                color = KalyanTheme.colors.secondaryText,
                textAlign = TextAlign.Center
            )
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        delay(1000L)
        rootController.present("account_login", launchFlag = LaunchFlag.SingleNewTask)
    })
}