package screens.main.admin_add_tabacco

sealed class AccountCreateEvent {
    data class ChangeLogin(val value: String) : AccountCreateEvent()
    data class ChangeName(val value: String) : AccountCreateEvent()
    data class ChangePassword(val value: String) : AccountCreateEvent()
    class ShowPasswordClick : AccountCreateEvent()
    data class ChangePasswordRepeat(val value: String) : AccountCreateEvent()
    class ShowPasswordRepeatClick : AccountCreateEvent()
    class CreateAccountClick : AccountCreateEvent()
    class OnBackClick : AccountCreateEvent()
    class ClearActions : AccountCreateEvent()
}

data class AccountCreateState(
    val isLoading: Boolean = false,
    val login: String = "",
    val name: String = "",
    val password: String = "",
    val isPasswordHidden: Boolean = true,
    val passwordRepeat: String = "",
    val isPasswordRepeatHidden: Boolean = true,
    val error: String = ""
)

sealed class AccountCreateAction {
    class OpenLoginScreen : AccountCreateAction()
    class ReturnToPreviousScreen : AccountCreateAction()
}
