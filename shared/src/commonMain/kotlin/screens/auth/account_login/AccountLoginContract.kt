package screens.auth.account_login

sealed class AccountLoginEvent {
    data class ChangeLogin(val value: String) : AccountLoginEvent()
    data class ChangePassword(val value: String) : AccountLoginEvent()
    class CreateAccountClick : AccountLoginEvent()
    class ForgotPasswordClick : AccountLoginEvent()
    class SendClick : AccountLoginEvent()
    class ClearActions : AccountLoginEvent()
}

data class AccountLoginState(
    val isLoading: Boolean = false,
    val login: String = "",
    val password: String = "",
    val error: String = "",
    val isButtonEnabled: Boolean = false
)

sealed class AccountLoginAction {
    class OpenCreateAccountScreen : AccountLoginAction()
    class OpenForgotPasswordScreen : AccountLoginAction()
    class OpenMainScreen : AccountLoginAction()
}
