package screens.login.get_sms

sealed class GetSmsEvent {
    data class ChangeCode(val value: String) : GetSmsEvent()
    class NextClick : GetSmsEvent()
}

data class GetSmsState(
    val code: String = "",
    val isButtonEnabled: Boolean = false
)

sealed class GetSmsAction {
    class OpenMainScreen : GetSmsAction()
}
