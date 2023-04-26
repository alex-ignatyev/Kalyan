package screens.auth.account_login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kalyan.shared.strings.AppResStrings
import screens.auth.account_login.AccountLoginEvent.ChangeLogin
import screens.auth.account_login.AccountLoginEvent.ChangePassword
import screens.auth.account_login.AccountLoginEvent.CreateAccountClick
import screens.auth.account_login.AccountLoginEvent.ForgotPasswordClick
import screens.auth.account_login.AccountLoginEvent.PasswordShowClick
import screens.auth.account_login.AccountLoginEvent.SendClick
import ui.themes.KalyanTheme
import ui.themes.components.KalyanButton
import ui.themes.components.KalyanCircularProgress
import ui.themes.components.KalyanTextField
import ui.themes.components.TextFieldType

@Composable
fun AccountLoginView(state: AccountLoginState, obtainEvent: (AccountLoginEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = AppResStrings.title_login,
            modifier = Modifier.padding(top = 32.dp),
            style = KalyanTheme.typography.header,
            color = KalyanTheme.colors.primaryText
        )

        Text(
            text = AppResStrings.subtitle_login,
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
            value = state.password,
            placeholder = AppResStrings.text_password,
            enabled = !state.isLoading,
            isError = state.error.isNotBlank(),
            fieldType = TextFieldType.Password(state.isPasswordHidden),
            endIcon = { PasswordShowIcon(state.isPasswordHidden, obtainEvent) }
        ) {
            obtainEvent(ChangePassword(it))
        }

        Row(modifier = Modifier.padding(end = 16.dp, top = 16.dp).fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = AppResStrings.text_password_forgot,
                color = KalyanTheme.colors.generalColor,
                fontSize = 12.sp,
                modifier = Modifier.clickable {
                    obtainEvent.invoke(ForgotPasswordClick())
                }
            )
        }

        KalyanButton(
            modifier = Modifier.padding(vertical = 32.dp).padding(top = 24.dp),
            enabled = !state.isLoading,
            content = {
                if (state.isLoading) {
                    KalyanCircularProgress()
                } else {
                    Text(
                        text = AppResStrings.title_login,
                        style = KalyanTheme.typography.body,
                        color = KalyanTheme.colors.primaryText
                    )
                }
            },
            onClick = { obtainEvent(SendClick()) }
        )

        Text(
            text = state.error,
            color = KalyanTheme.colors.errorColor,
            modifier = Modifier.padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = AppResStrings.text_account_dont_have,
                color = KalyanTheme.colors.secondaryText
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = AppResStrings.text_account_create_one,
                color = KalyanTheme.colors.generalColor,
                style = KalyanTheme.typography.body,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    obtainEvent.invoke(CreateAccountClick())
                }
            )
        }
    }
}

@Composable
fun PasswordShowIcon(isPasswordHidden: Boolean, obtainEvent: (AccountLoginEvent) -> Unit) {
    Icon(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            obtainEvent.invoke(PasswordShowClick())
        },
        imageVector = if (isPasswordHidden) {
            Icons.Sharp.KeyboardArrowUp
        } else {
            Icons.Default.KeyboardArrowDown
        },
        contentDescription = null,
        tint = KalyanTheme.colors.secondaryText
    )
}
