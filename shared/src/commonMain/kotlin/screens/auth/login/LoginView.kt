package screens.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kalyan.shared.AppRes
import io.github.skeptick.libres.compose.painterResource
import screens.auth.login.LoginEvent.ChangePhone
import screens.auth.login.LoginEvent.NextClick
import ui.themes.KalyanTheme
import ui.themes.components.KalyanButton
import ui.themes.components.KalyanTextField

@Composable
fun LoginView(state: LoginState, obtainEvent: (LoginEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(AppRes.image.hookah),
            "",
            colorFilter = ColorFilter.tint(KalyanTheme.colors.generalColor),
            modifier = Modifier.padding(top = 32.dp)
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Введите номер телефона",
            fontSize = 16.sp,
            color = KalyanTheme.colors.secondaryText
        )

        KalyanTextField(state.phone) {
            obtainEvent(ChangePhone(it))
        }

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
