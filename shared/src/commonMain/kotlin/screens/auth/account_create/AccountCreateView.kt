package screens.auth.account_create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import screens.auth.account_create.AccountCreateEvent.ChangeLogin
import screens.auth.account_create.AccountCreateEvent.ChangeName
import screens.auth.account_create.AccountCreateEvent.ChangePassword
import screens.auth.account_create.AccountCreateEvent.ChangeRepeatPassword
import screens.auth.account_create.AccountCreateEvent.SendClick
import ui.themes.components.KalyanButton
import ui.themes.components.KalyanTextField

@Composable
fun AccountCreateView(state: AccountCreateState, obtainEvent: (AccountCreateEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        KalyanTextField(state.login, placeholder = "Логин") {
            obtainEvent(ChangeLogin(it))
        }

        KalyanTextField(state.name, placeholder = "Имя") {
            obtainEvent(ChangeName(it))
        }

        KalyanTextField(state.password, placeholder = "Пароль") {
            obtainEvent(ChangePassword(it))
        }

        KalyanTextField(state.repeatPassword, placeholder = "Повторить пароль") {
            obtainEvent(ChangeRepeatPassword(it))
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
