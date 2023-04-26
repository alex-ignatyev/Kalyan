package screens.auth.account_forgot

sealed class AccountForgotEvent {
    data class ChangeLogin(val value: String) : AccountForgotEvent()
    data class ChangeNewPassword(val value: String) : AccountForgotEvent()
    class NewPasswordShowClick : AccountForgotEvent()
    data class ChangeRepeatNewPassword(val value: String) : AccountForgotEvent()
    class RepeatNewPasswordShowClick : AccountForgotEvent()
    class SendClick : AccountForgotEvent()
    class ClearActions : AccountForgotEvent()
}

data class AccountForgotState(
    val isLoading: Boolean = false,
    val login: String = "",
    val newPassword: String = "",
    val isNewPasswordHidden: Boolean = true,
    val repeatNewPassword: String = "",
    val isRepeatNewPasswordHidden: Boolean = true,
    val error: String = ""
)

sealed class AccountForgotAction {
    class OpenLoginScreen : AccountForgotAction()
}
