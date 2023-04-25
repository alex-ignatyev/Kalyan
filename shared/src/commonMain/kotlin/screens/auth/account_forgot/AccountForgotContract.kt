package screens.auth.account_forgot

sealed class AccountForgotEvent {
    data class ChangeLogin(val value: String) : AccountForgotEvent()
    data class ChangeNewPassword(val value: String) : AccountForgotEvent()
    data class ChangeRepeatNewPassword(val value: String) : AccountForgotEvent()
    class SendClick : AccountForgotEvent()
    class ClearActions : AccountForgotEvent()
}

data class AccountForgotState(
    val isLoading: Boolean = false,
    val login: String = "",
    val newPassword: String = "",
    val repeatNewPassword: String = "",
    val error: String = "",
    val isButtonEnabled: Boolean = false
)

sealed class AccountForgotAction {
    class OpenLoginScreen : AccountForgotAction()
}
