package screens.auth.get_sms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.kalyan.shared.AppRes
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.LaunchFlag.SingleNewTask
import screens.auth.get_sms.GetSmsAction.OpenMainScreen
import screens.auth.get_sms.GetSmsEvent.ChangeCode
import screens.auth.get_sms.GetSmsEvent.NextClick
import ui.themes.KalyanTheme
import ui.themes.components.KalyanButton
import ui.themes.components.KalyanTextField

@Composable
internal fun GetSmsScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { GetSmsViewModel() }) { viewModel ->
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = viewState.text,
                fontSize = 32.sp,
                color = KalyanTheme.colors.primaryText,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )

            KalyanTextField(viewState.code) {
                viewModel.obtainEvent(ChangeCode(it))
            }

            KalyanButton(
                modifier = Modifier.padding(vertical = 32.dp),
                text = AppRes.string.action_send,
                enabled = viewState.isButtonEnabled,
                content = {
                    CircularProgressIndicator()
                },
                onClick = {
                    viewModel.obtainEvent(NextClick())
                })
        }

        when (viewAction) {
            is OpenMainScreen -> rootController.present("main", launchFlag = SingleNewTask)
            null -> {}
        }
    }
}
