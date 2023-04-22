package screens.auth.get_sms

sealed class GetSmsEvent {
    data class ChangeCode(val value: String) : GetSmsEvent()
    class NextClick : GetSmsEvent()
}

data class GetSmsState(
    val code: String = "",
    val text :String = "Sms Code",
    val isButtonEnabled: Boolean = false
)

sealed class GetSmsAction {
    class OpenMainScreen : GetSmsAction()
}
