package screens.auth.account_login

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.AuthRepository
import kotlinx.coroutines.launch
import model.auth.request.AccountLoginRequest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.auth.account_login.AccountLoginAction.OpenCreateAccountScreen
import screens.auth.account_login.AccountLoginAction.OpenForgotPasswordScreen
import screens.auth.account_login.AccountLoginAction.OpenMainScreen
import screens.auth.account_login.AccountLoginEvent.ChangeLogin
import screens.auth.account_login.AccountLoginEvent.ChangePassword
import screens.auth.account_login.AccountLoginEvent.ClearActions
import screens.auth.account_login.AccountLoginEvent.CreateAccountClick
import screens.auth.account_login.AccountLoginEvent.ForgotPasswordClick
import screens.auth.account_login.AccountLoginEvent.LoginClick
import screens.auth.account_login.AccountLoginEvent.ShowPasswordClick
import utils.EMPTY
import utils.answer.onFailure
import utils.answer.onSuccess

class AccountLoginViewModel : KoinComponent,
    BaseSharedViewModel<AccountLoginState, AccountLoginAction, AccountLoginEvent>(
        initialState = AccountLoginState()
    ) {

    private val repository: AuthRepository by inject()

    override fun obtainEvent(viewEvent: AccountLoginEvent) {
        when (viewEvent) {
            is ChangeLogin -> changeLogin(viewEvent.value)
            is ChangePassword -> changePassword(viewEvent.value)
            is CreateAccountClick -> openCreateAccountScreen()
            is ForgotPasswordClick -> openForgotPasswordScreen()
            is ShowPasswordClick -> changePasswordVisible()
            is LoginClick -> login()
            is ClearActions -> clearActions()
        }
    }

    private fun changeLogin(login: String) {
        viewState = viewState.copy(login = login, error = EMPTY)
    }

    private fun changePassword(password: String) {
        viewState = viewState.copy(password = password, error = EMPTY)
    }

    private fun changePasswordVisible() {
        val passwordVisible = !viewState.isPasswordHidden
        viewState = viewState.copy(isPasswordHidden = passwordVisible)
    }

    private fun login() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true, error = EMPTY)
            val request = AccountLoginRequest(
                login = viewState.login,
                password = viewState.password
            )
            repository.login(request).onSuccess {
                viewAction = OpenMainScreen()
            }.onFailure {
                viewState = viewState.copy(isLoading = false, error = it.message)
            }
        }
    }

    private fun openCreateAccountScreen() {
        viewAction = OpenCreateAccountScreen()
    }

    private fun openForgotPasswordScreen() {
        viewAction = OpenForgotPasswordScreen()
    }

    private fun clearActions() {
        viewAction = null
        viewState = viewState.copy(isLoading = false, error = EMPTY)
    }
}
