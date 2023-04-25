package screens.auth.account_create

sealed class AccountCreateEvent {
    data class ChangeLogin(val value: String) : AccountCreateEvent()
    data class ChangeName(val value: String) : AccountCreateEvent()
    data class ChangePassword(val value: String) : AccountCreateEvent()
    data class ChangeRepeatPassword(val value: String) : AccountCreateEvent()
    class SendClick : AccountCreateEvent()
    class ClearActions : AccountCreateEvent()
}

data class AccountCreateState(
    val isLoading: Boolean = false,
    val login: String = "",
    val name: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val error: String = "",
    val isButtonEnabled: Boolean = false
)

sealed class AccountCreateAction {
    class OpenLoginScreen : AccountCreateAction()
}
