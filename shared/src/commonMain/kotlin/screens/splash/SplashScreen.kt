package screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.adeo.kviewmodel.odyssey.StoredViewModel
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag
import screens.splash.SplashAction.OpenFlow
import screens.splash.SplashEvent.InitSplashScreen
import ui.KalyanTheme

@Composable
internal fun SplashScreen() {
    val rootController = LocalRootController.current

    StoredViewModel({ SplashViewModel() }) { viewModel ->
        val action by viewModel.viewActions().collectAsState(null)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Kalyan",
                style = KalyanTheme.typography.header,
                color = KalyanTheme.colors.primaryText,
                textAlign = TextAlign.Center
            )
        }

        viewModel.obtainEvent(InitSplashScreen())

        when (action) {
            is OpenFlow -> rootController.present((action as OpenFlow).flow, launchFlag = LaunchFlag.SingleNewTask)
            else -> {}
        }
    }
}
