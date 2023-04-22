package screens.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kalyan.shared.AppRes
import screens.auth.login.LoginEvent.ChangePhone
import screens.auth.login.LoginEvent.NextClick
import ui.themes.KalyanTheme
import ui.themes.components.KalyanButton

@Composable
fun LoginView(state: LoginState, obtainEvent: (LoginEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(KalyanTheme.colors.secondaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "Login",
            fontSize = 32.sp,
            color = KalyanTheme.colors.primaryText,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )

        OutlinedTextField(
            modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth().height(48.dp),
            value = state.phone,
            onValueChange = {
                obtainEvent(ChangePhone(it))
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

        KalyanButton(
            modifier = Modifier.padding(vertical = 32.dp),
            text = if (state.isLoading) null else AppRes.string.action_send,
            enabled = state.isButtonEnabled,
            content = {
                CircularProgressIndicator()
            },
            onClick = {
                obtainEvent(NextClick())
            })
    }
}
