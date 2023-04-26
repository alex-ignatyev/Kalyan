package screens.auth.account_create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Sharp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import com.kalyan.shared.strings.AppResStrings
import screens.auth.account_create.AccountCreateEvent.ChangeLogin
import screens.auth.account_create.AccountCreateEvent.ChangeName
import screens.auth.account_create.AccountCreateEvent.ChangePassword
import screens.auth.account_create.AccountCreateEvent.ChangeRepeatPassword
import screens.auth.account_create.AccountCreateEvent.CreateAccountClick
import screens.auth.account_create.AccountCreateEvent.PasswordShowClick
import screens.auth.account_create.AccountCreateEvent.RepeatPasswordShowClick
import ui.themes.KalyanTheme
import ui.themes.components.KalyanButton
import ui.themes.components.KalyanCircularProgress
import ui.themes.components.KalyanTextField
import ui.themes.components.TextFieldType

@Composable
fun AccountCreateView(state: AccountCreateState, obtainEvent: (AccountCreateEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = AppRes.string.text_account_create,
            modifier = Modifier.padding(top = 32.dp),
            style = KalyanTheme.typography.header,
            color = KalyanTheme.colors.primaryText
        )

        Spacer(modifier = Modifier.height(48.dp))

        KalyanTextField(
            value = state.login,
            placeholder = AppResStrings.text_login,
            enabled = !state.isLoading,
            isError = state.error.isNotBlank(),
        ) {
            obtainEvent(ChangeLogin(it))
        }

        KalyanTextField(
            value = state.name,
            placeholder = AppResStrings.text_account_name,
            enabled = !state.isLoading,
            isError = state.error.isNotBlank()
        ) {
            obtainEvent(ChangeName(it))
        }

        KalyanTextField(
            value = state.password,
            placeholder = AppResStrings.text_password,
            enabled = !state.isLoading,
            isError = state.error.isNotBlank(),
            fieldType = TextFieldType.Password(state.isPasswordHidden),
            endIcon = {
                Icon(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        obtainEvent.invoke(PasswordShowClick())
                    },
                    imageVector = if (state.isPasswordHidden) {
                        Sharp.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = null,
                    tint = KalyanTheme.colors.secondaryText
                )
            }
        ) {
            obtainEvent(ChangePassword(it))
        }

        KalyanTextField(
            value = state.repeatPassword,
            placeholder = AppResStrings.text_password_repeat,
            enabled = !state.isLoading,
            isError = state.error.isNotBlank(),
            fieldType = TextFieldType.Password(state.isRepeatPasswordHidden),
            endIcon = {
                Icon(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        obtainEvent.invoke(RepeatPasswordShowClick())
                    },
                    imageVector = if (state.isRepeatPasswordHidden) {
                        Sharp.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = null,
                    tint = KalyanTheme.colors.secondaryText
                )
            }
        ) {
            obtainEvent(ChangeRepeatPassword(it))
        }

        KalyanButton(
            modifier = Modifier.padding(vertical = 32.dp),
            text = if (state.isLoading) null else AppRes.string.text_account_create,
            enabled = !state.isLoading,
            content = {
                KalyanCircularProgress()
            },
            onClick = {
                obtainEvent(CreateAccountClick())
            })

        Text(
            text = state.error,
            color = KalyanTheme.colors.errorColor,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
