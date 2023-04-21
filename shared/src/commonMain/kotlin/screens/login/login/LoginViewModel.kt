package screens.login.login

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.AuthRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.login.login.LoginAction.OpenSmsScreen
import screens.login.login.LoginEvent.ChangePhone
import screens.login.login.LoginEvent.NextClick
import utils.answer.onFailure
import utils.answer.onSuccess

class LoginViewModel : KoinComponent, BaseSharedViewModel<LoginState, LoginAction, LoginEvent>(
    initialState = LoginState()
) {

    private val repository: AuthRepository by inject()

    override fun obtainEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is ChangePhone -> changePhone(viewEvent.value)
            is NextClick -> openSmsScreen()
        }
    }

    private fun changePhone(phone: String) {
        viewState = viewState.copy(phone = phone, isButtonEnabled = phone.isNotBlank())
    }

    private fun openSmsScreen() {
        viewModelScope.launch {
            repository.logIn(viewState.phone).onSuccess {
                viewAction = OpenSmsScreen()
            }.onFailure {
                print(it.message)
            }
        }
    }
}