package screens.login.get_sms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import screens.login.get_sms.GetSmsAction.OpenMainScreen
import screens.login.get_sms.GetSmsEvent.ChangeCode
import screens.login.get_sms.GetSmsEvent.NextClick
import ui.themes.KalyanTheme
import ui.themes.components.JetHabitButton

@Composable
internal fun GetSmsScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { GetSmsViewModel() }) { viewModel ->
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)

        Column(
            modifier = Modifier.fillMaxSize().background(KalyanTheme.colors.secondaryBackground),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Get Sms",
                fontSize = 32.sp,
                color = KalyanTheme.colors.primaryText,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )

            OutlinedTextField(
                modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth().height(48.dp),
                value = viewState.code,
                onValueChange = {
                    viewModel.obtainEvent(ChangeCode(it))
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = KalyanTheme.colors.primaryBackground,
                    unfocusedBorderColor = KalyanTheme.colors.primaryBackground,
                    disabledBorderColor = KalyanTheme.colors.primaryBackground,
                    errorBorderColor = KalyanTheme.colors.primaryBackground,
                    backgroundColor = KalyanTheme.colors.primaryBackground,
                    textColor = KalyanTheme.colors.primaryText,
                    cursorColor = KalyanTheme.colors.controlColor
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            JetHabitButton(
                modifier = Modifier.padding(vertical = 44.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                text = AppRes.string.action_next,
                enabled = viewState.isButtonEnabled,
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
