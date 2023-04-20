package screens.login.login

import com.adeo.kviewmodel.BaseSharedViewModel
import screens.login.login.LoginAction.OpenSmsScreen
import screens.login.login.LoginEvent.ChangePhone
import screens.login.login.LoginEvent.NextClick

class LoginViewModel : BaseSharedViewModel<LoginState, LoginAction, LoginEvent>(
    initialState = LoginState()
) {

    override fun obtainEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is ChangePhone -> changePhone(viewEvent.value)
            is NextClick -> viewAction = OpenSmsScreen()
        }
    }

    private fun changePhone(phone: String) {
        viewState = viewState.copy(phone = phone, isButtonEnabled = phone.isNotBlank())
    }
}