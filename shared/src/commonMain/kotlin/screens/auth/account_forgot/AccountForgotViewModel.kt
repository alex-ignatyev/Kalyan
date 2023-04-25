package screens.auth.account_forgot

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.AuthRepository
import kotlinx.coroutines.launch
import model.data.request.AccountForgotRequest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.auth.account_forgot.AccountForgotAction.OpenLoginScreen
import screens.auth.account_forgot.AccountForgotEvent.ChangeLogin
import screens.auth.account_forgot.AccountForgotEvent.ChangeNewPassword
import screens.auth.account_forgot.AccountForgotEvent.ChangeRepeatNewPassword
import screens.auth.account_forgot.AccountForgotEvent.ClearActions
import screens.auth.account_forgot.AccountForgotEvent.SendClick
import utils.answer.onFailure
import utils.answer.onSuccess

class AccountForgotViewModel : KoinComponent,
    BaseSharedViewModel<AccountForgotState, AccountForgotAction, AccountForgotEvent>(
        initialState = AccountForgotState()
    ) {

    private val repository: AuthRepository by inject()

    override fun obtainEvent(viewEvent: AccountForgotEvent) {
        when (viewEvent) {
            is ChangeLogin -> changeLogin(viewEvent.value)
            is ChangeNewPassword -> changeNewPassword(viewEvent.value)
            is ChangeRepeatNewPassword -> changeRepeatNewPassword(viewEvent.value)
            is SendClick -> resetPassword()
            is ClearActions -> clearActions()
        }
    }

    private fun changeLogin(login: String) {
        viewState = viewState.copy(login = login, isButtonEnabled = isButtonEnabled())
    }

    private fun changeNewPassword(newPassword: String) {
        viewState = viewState.copy(newPassword = newPassword, isButtonEnabled = isButtonEnabled())
    }

    private fun changeRepeatNewPassword(repeatNewPassword: String) {
        viewState = viewState.copy(repeatNewPassword = repeatNewPassword, isButtonEnabled = isButtonEnabled())
    }

    private fun isButtonEnabled(): Boolean {
        return viewState.login.length >= 4 && viewState.newPassword.length >= 4 && viewState.repeatNewPassword.length >= 4
    }

    private fun resetPassword() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true, isButtonEnabled = false)
            val request = AccountForgotRequest(
                login = viewState.login,
                newPassword = viewState.newPassword,
                repeatNewPassword = viewState.repeatNewPassword
            )
            repository.forgot(request).onSuccess {
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
