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
import screens.auth.account_forgot.AccountForgotEvent.NewPasswordShowClick
import screens.auth.account_forgot.AccountForgotEvent.RepeatNewPasswordShowClick
import screens.auth.account_forgot.AccountForgotEvent.SendClick
import utils.EMPTY
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
            is NewPasswordShowClick -> changeNewPasswordVisibility()
            is ChangeRepeatNewPassword -> changeRepeatNewPassword(viewEvent.value)
            is RepeatNewPasswordShowClick -> changeRepeatNewPasswordVisibility()
            is SendClick -> resetPassword()
            is ClearActions -> clearActions()
        }
    }

    private fun changeLogin(login: String) {
        viewState = viewState.copy(login = login, error = EMPTY)
    }

    private fun changeNewPassword(newPassword: String) {
        viewState = viewState.copy(newPassword = newPassword, error = EMPTY)
    }

    private fun changeRepeatNewPassword(repeatNewPassword: String) {
        viewState = viewState.copy(repeatNewPassword = repeatNewPassword, error = EMPTY)
    }

    private fun changeNewPasswordVisibility() {
        val passwordVisible = !viewState.isNewPasswordHidden
        viewState = viewState.copy(isNewPasswordHidden = passwordVisible)
    }

    private fun changeRepeatNewPasswordVisibility() {
        val passwordVisible = !viewState.isRepeatNewPasswordHidden
        viewState = viewState.copy(isRepeatNewPasswordHidden = passwordVisible)
    }

    private fun resetPassword() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            val request = AccountForgotRequest(
                login = viewState.login,
                newPassword = viewState.newPassword,
                repeatNewPassword = viewState.repeatNewPassword
            )
            repository.forgot(request).onSuccess {
                viewAction = OpenLoginScreen()
            }.onFailure {
                viewState = viewState.copy(isLoading = false, error = it.message)
            }
        }
    }

    private fun clearActions() {
        viewAction = null
        viewState = viewState.copy(isLoading = false, error = EMPTY)
    }
}
