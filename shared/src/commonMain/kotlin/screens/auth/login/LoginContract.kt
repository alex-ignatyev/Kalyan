package screens.auth.login

sealed class LoginEvent {
    data class ChangePhone(val value: String) : LoginEvent()
    class NextClick : LoginEvent()
    class ClearActions : LoginEvent()
}

data class LoginState(
    val isLoading: Boolean = false,
    val phone: String = "",
    val isButtonEnabled: Boolean = false
)

sealed class LoginAction {
    class OpenSmsScreen : LoginAction()
}
