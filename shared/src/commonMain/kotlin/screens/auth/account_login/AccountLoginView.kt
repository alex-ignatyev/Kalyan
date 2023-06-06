package screens.auth.account_login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
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
import com.kalyan.shared.AppRes
import com.kalyan.shared.strings.AppResStrings
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.ime
import com.moriatsushi.insetsx.navigationBars
import screens.auth.account_login.AccountLoginEvent.ChangeLogin
import screens.auth.account_login.AccountLoginEvent.ChangePassword
import screens.auth.account_login.AccountLoginEvent.CreateAccountClick
import screens.auth.account_login.AccountLoginEvent.ForgotPasswordClick
import screens.auth.account_login.AccountLoginEvent.LoginClick
import screens.auth.account_login.AccountLoginEvent.ShowPasswordClick
import ui.KalyanTheme
import ui.components.KalyanButton
import ui.components.KalyanCircularProgress
import ui.components.KalyanTextField
import ui.components.TextFieldType.Password

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun AccountLoginView(state: AccountLoginState = AccountLoginState(), obtainEvent: (AccountLoginEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(KalyanTheme.colors.primaryBackground)
            .windowInsetsPadding(WindowInsets.ime)
            .windowInsetsPadding(WindowInsets.navigationBars),
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
            fieldType = Password(state.isPasswordHidden),
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
            modifier = Modifier.padding(top = 24.dp).padding(vertical = 32.dp),
            text = if (state.isLoading) null else AppRes.string.title_login,
            enabled = !state.isLoading,
            content = {
                KalyanCircularProgress()
            },
            onClick = { obtainEvent(LoginClick()) }
        )

        Text(
            text = state.error,
            color = KalyanTheme.colors.errorColor,
            modifier = Modifier.padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(horizontal = 16.dp),
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
            obtainEvent.invoke(ShowPasswordClick())
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
