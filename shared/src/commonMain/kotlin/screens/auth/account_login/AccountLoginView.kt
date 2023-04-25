package screens.auth.account_login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import screens.auth.account_login.AccountLoginEvent.ChangeLogin
import screens.auth.account_login.AccountLoginEvent.ChangePassword
import screens.auth.account_login.AccountLoginEvent.CreateAccountClick
import screens.auth.account_login.AccountLoginEvent.ForgotPasswordClick
import screens.auth.account_login.AccountLoginEvent.SendClick
import ui.themes.components.KalyanButton
import ui.themes.components.KalyanTextField

@Composable
fun AccountLoginView(state: AccountLoginState, obtainEvent: (AccountLoginEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        KalyanTextField(state.login, hint = "Логин") {
            obtainEvent(ChangeLogin(it))
        }

        KalyanTextField(state.password, hint = "Пароль") {
            obtainEvent(ChangePassword(it))
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

        KalyanButton(
            modifier = Modifier.padding(vertical = 32.dp),
            text = "Создать аккаунт",
            content = {
                CircularProgressIndicator()
            },
            onClick = {
                obtainEvent(CreateAccountClick())
            })

        KalyanButton(
            modifier = Modifier.padding(vertical = 32.dp),
            text = "Восстановить аккаунт",
            content = {
                CircularProgressIndicator()
            },
            onClick = {
                obtainEvent(ForgotPasswordClick())
            })
    }
}
