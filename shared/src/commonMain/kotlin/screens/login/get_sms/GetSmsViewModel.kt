package screens.login.get_sms

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.AuthRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.login.get_sms.GetSmsAction.OpenMainScreen
import screens.login.get_sms.GetSmsEvent.ChangeCode
import screens.login.get_sms.GetSmsEvent.NextClick
import utils.answer.onFailure
import utils.answer.onSuccess

class GetSmsViewModel : KoinComponent, BaseSharedViewModel<GetSmsState, GetSmsAction, GetSmsEvent>(
    initialState = GetSmsState()
) {

    private val repository: AuthRepository by inject()

    override fun obtainEvent(viewEvent: GetSmsEvent) {
        when (viewEvent) {
            is ChangeCode -> changeCode(viewEvent.value)
            is NextClick -> openMainScreen()
        }
    }

    private fun changeCode(code: String) {
        viewState = viewState.copy(code = code, isButtonEnabled = code.isNotBlank())
    }

    private fun openMainScreen() {
        viewModelScope.launch {
            repository.sendSmsCode(viewState.code).onSuccess {
                viewAction = OpenMainScreen()
            }.onFailure {
                print(it.message)
            }
        }
    }
}
