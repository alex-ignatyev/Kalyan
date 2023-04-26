package screens.auth.account_create

sealed class AccountCreateEvent {
    data class ChangeLogin(val value: String) : AccountCreateEvent()
    data class ChangeName(val value: String) : AccountCreateEvent()
    data class ChangePassword(val value: String) : AccountCreateEvent()
    class PasswordShowClick : AccountCreateEvent()
    data class ChangeRepeatPassword(val value: String) : AccountCreateEvent()
    class RepeatPasswordShowClick : AccountCreateEvent()
    class CreateAccountClick : AccountCreateEvent()
    class ClearActions : AccountCreateEvent()
}

data class AccountCreateState(
    val isLoading: Boolean = false,
    val login: String = "",
    val name: String = "",
    val password: String = "",
    val isPasswordHidden: Boolean = true,
    val repeatPassword: String = "",
    val isRepeatPasswordHidden: Boolean = true,
    val error: String = ""
)

sealed class AccountCreateAction {
    class OpenLoginScreen : AccountCreateAction()
}
