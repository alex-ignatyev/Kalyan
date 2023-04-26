package screens.auth.account_forgot

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import com.kalyan.shared.strings.AppResStrings
import screens.auth.account_forgot.AccountForgotEvent.ChangeLogin
import screens.auth.account_forgot.AccountForgotEvent.ChangeNewPassword
import screens.auth.account_forgot.AccountForgotEvent.ChangeRepeatNewPassword
import screens.auth.account_forgot.AccountForgotEvent.NewPasswordShowClick
import screens.auth.account_forgot.AccountForgotEvent.RepeatNewPasswordShowClick
import screens.auth.account_forgot.AccountForgotEvent.SendClick
import ui.themes.KalyanTheme
import ui.themes.components.KalyanButton
import ui.themes.components.KalyanCircularProgress
import ui.themes.components.KalyanTextField
import ui.themes.components.TextFieldType

@Composable
fun AccountForgotView(state: AccountForgotState, obtainEvent: (AccountForgotEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = AppResStrings.title_forgot,
            modifier = Modifier.padding(top = 32.dp),
            style = KalyanTheme.typography.header,
            color = KalyanTheme.colors.primaryText
        )

        Text(
            text = AppResStrings.subtitle_forgot,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
            style = KalyanTheme.typography.body,
            textAlign = TextAlign.Center,
            color = KalyanTheme.colors.secondaryText
        )

        Spacer(modifier = Modifier.height(40.dp))

        KalyanTextField(
            value = state.login,
            placeholder = AppResStrings.text_login,
            enabled = !state.isLoading,
            isError = state.error.isNotBlank(),
        ) {
            obtainEvent(ChangeLogin(it))
        }

        KalyanTextField(
            value = state.newPassword,
            placeholder = AppResStrings.text_password,
            enabled = !state.isLoading,
            isError = state.error.isNotBlank(),
            fieldType = TextFieldType.Password(state.isNewPasswordHidden),
            endIcon = {
                Icon(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        obtainEvent.invoke(NewPasswordShowClick())
                    },
                    imageVector = if (state.isNewPasswordHidden) {
                        Sharp.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = null,
                    tint = KalyanTheme.colors.secondaryText
                )
            }
        ) {
            obtainEvent(ChangeNewPassword(it))
        }

        KalyanTextField(
            value = state.repeatNewPassword,
            placeholder = AppResStrings.text_password_repeat,
            enabled = !state.isLoading,
            isError = state.error.isNotBlank(),
            fieldType = TextFieldType.Password(state.isRepeatNewPasswordHidden),
            endIcon = {
                Icon(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        obtainEvent.invoke(RepeatNewPasswordShowClick())
                    },
                    imageVector = if (state.isRepeatNewPasswordHidden) {
                        Sharp.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = null,
                    tint = KalyanTheme.colors.secondaryText
                )
            }
        ) {
            obtainEvent(ChangeRepeatNewPassword(it))
        }

        KalyanButton(
            modifier = Modifier.padding(vertical = 32.dp),
            text = if (state.isLoading) null else AppRes.string.action_send,
            enabled = !state.isLoading,
            content = {
                KalyanCircularProgress()
            },
            onClick = {
                obtainEvent(SendClick())
            })

        Text(
            text = state.error,
            color = KalyanTheme.colors.errorColor,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
