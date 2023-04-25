package screens.auth.account_forgot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import screens.auth.account_forgot.AccountForgotEvent.ChangeLogin
import screens.auth.account_forgot.AccountForgotEvent.ChangeNewPassword
import screens.auth.account_forgot.AccountForgotEvent.ChangeRepeatNewPassword
import screens.auth.account_forgot.AccountForgotEvent.SendClick
import ui.themes.components.KalyanButton
import ui.themes.components.KalyanTextField

@Composable
fun AccountForgotView(state: AccountForgotState, obtainEvent: (AccountForgotEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        KalyanTextField(state.login, placeholder = "Логин") {
            obtainEvent(ChangeLogin(it))
        }

        KalyanTextField(state.newPassword, placeholder = "Новый пароль") {
            obtainEvent(ChangeNewPassword(it))
        }

        KalyanTextField(state.repeatNewPassword, placeholder = "Повторите новый пароль") {
            obtainEvent(ChangeRepeatNewPassword(it))
        }

        KalyanButton(
            modifier = Modifier.padding(vertical = 32.dp),
            text = if (state.isLoading) null else AppRes.string.action_send,
            enabled = state.isButtonEnabled,
            content = {
                CircularProgressIndicator()
            },
            onClick = {
                obtainEvent(SendClick())
            })
    }
}
