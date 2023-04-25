package screens.auth.account_create

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.AuthRepository
import kotlinx.coroutines.launch
import model.data.request.AccountCreateRequest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.auth.account_create.AccountCreateAction.OpenLoginScreen
import screens.auth.account_create.AccountCreateEvent.ChangeLogin
import screens.auth.account_create.AccountCreateEvent.ChangeName
import screens.auth.account_create.AccountCreateEvent.ChangePassword
import screens.auth.account_create.AccountCreateEvent.ChangeRepeatPassword
import screens.auth.account_create.AccountCreateEvent.ClearActions
import screens.auth.account_create.AccountCreateEvent.SendClick
import utils.answer.onFailure
import utils.answer.onSuccess

//TODO Обработать все кейсы
class AccountCreateViewModel : KoinComponent,
    BaseSharedViewModel<AccountCreateState, AccountCreateAction, AccountCreateEvent>(
        initialState = AccountCreateState()
    ) {

    private val repository: AuthRepository by inject()

    override fun obtainEvent(viewEvent: AccountCreateEvent) {
        when (viewEvent) {
            is ChangeLogin -> changeLogin(viewEvent.value)
            is ChangeName -> changeName(viewEvent.value)
            is ChangePassword -> changePassword(viewEvent.value)
            is ChangeRepeatPassword -> changeRepeatPassword(viewEvent.value)
            is SendClick -> createAccount()
            is ClearActions -> clearActions()
        }
    }

    private fun changeLogin(login: String) {
        viewState = viewState.copy(login = login)
        viewState = viewState.copy(isButtonEnabled = isButtonEnabled())
    }

    private fun changeName(name: String) {
        viewState = viewState.copy(name = name)
        viewState = viewState.copy(isButtonEnabled = isButtonEnabled())
    }

    private fun changePassword(password: String) {
        viewState = viewState.copy(password = password)
        viewState = viewState.copy(isButtonEnabled = isButtonEnabled())
    }

    private fun changeRepeatPassword(repeatPassword: String) {
        viewState = viewState.copy(repeatPassword = repeatPassword)
        viewState = viewState.copy(isButtonEnabled = isButtonEnabled())
    }

    private fun isButtonEnabled(): Boolean {
        return viewState.login.length >= 4 && viewState.name.length >= 4 && viewState.password.length >= 4 && viewState.repeatPassword.length >= 4
    }

    private fun createAccount() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true, isButtonEnabled = false)
            val request = AccountCreateRequest(
                login = viewState.login,
                name = viewState.name,
                password = viewState.password,
                repeatPassword = viewState.repeatPassword
            )
            repository.create(request).onSuccess {
                viewAction = OpenLoginScreen()
            }.onFailure {
                viewState = viewState.copy(isLoading = false, isButtonEnabled = isButtonEnabled(), error = it.message)
            }
        }
    }

    private fun clearActions() {
        viewAction = null
        viewState = viewState.copy(isLoading = false, isButtonEnabled = isButtonEnabled())
    }
}
