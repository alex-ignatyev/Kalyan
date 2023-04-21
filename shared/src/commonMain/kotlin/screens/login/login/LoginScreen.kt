package screens.login.login

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
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import screens.login.login.LoginAction.OpenSmsScreen
import screens.login.login.LoginEvent.ChangePhone
import screens.login.login.LoginEvent.NextClick
import ui.themes.JetHabitTheme
import ui.themes.components.JetHabitButton

@Composable
internal fun LoginScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { LoginViewModel() }) { viewModel ->
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)

        Column(
            modifier = Modifier.fillMaxSize().background(JetHabitTheme.colors.secondaryBackground),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Login",
                fontSize = 32.sp,
                color = JetHabitTheme.colors.primaryText,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )

            OutlinedTextField(
                modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth().height(48.dp),
                value = viewState.phone,
                onValueChange = {
                    viewModel.obtainEvent(ChangePhone(it))
                },
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = JetHabitTheme.colors.primaryBackground,
                    unfocusedBorderColor = JetHabitTheme.colors.primaryBackground,
                    disabledBorderColor = JetHabitTheme.colors.primaryBackground,
                    errorBorderColor = JetHabitTheme.colors.primaryBackground,
                    backgroundColor = JetHabitTheme.colors.primaryBackground,
                    textColor = JetHabitTheme.colors.primaryText,
                    cursorColor = JetHabitTheme.colors.controlColor
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
            is OpenSmsScreen -> rootController.push("get_sms")
            null -> {}
        }
    }
}