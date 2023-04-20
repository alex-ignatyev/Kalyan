package screens.login.login

sealed class LoginEvent {
    data class ChangePhone(val value: String) : LoginEvent()
    class NextClick : LoginEvent()
}

data class LoginState(
    val phone: String = "",
    val isButtonEnabled: Boolean = false
)

sealed class LoginAction {
    class OpenSmsScreen : LoginAction()
}
