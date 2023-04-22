package screens.auth.login

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.auth.login.LoginAction.OpenSmsScreen
import screens.auth.login.LoginEvent.ChangePhone
import screens.auth.login.LoginEvent.ClearActions
import screens.auth.login.LoginEvent.NextClick
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
            is ClearActions -> clearActions()
        }
    }

    private fun changePhone(phone: String) {
        viewState = viewState.copy(phone = phone, isButtonEnabled = phone.isNotBlank())
    }

    private fun openSmsScreen() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true, isButtonEnabled = false)
            delay(2000L)
            repository.logIn(viewState.phone).onSuccess {
                viewAction = OpenSmsScreen()
            }.onFailure {
                print(it.message)
            }
        }
    }

    private fun clearActions() {
        viewAction = null
        viewState = viewState.copy(isLoading = false, isButtonEnabled = true)
    }
}
