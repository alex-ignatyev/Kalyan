package screens.login.get_sms

import com.adeo.kviewmodel.BaseSharedViewModel
import screens.login.get_sms.GetSmsAction.OpenMainScreen
import screens.login.get_sms.GetSmsEvent.ChangeCode
import screens.login.get_sms.GetSmsEvent.NextClick

class GetSmsViewModel : BaseSharedViewModel<GetSmsState, GetSmsAction, GetSmsEvent>(
    initialState = GetSmsState()
) {

    override fun obtainEvent(viewEvent: GetSmsEvent) {
        when (viewEvent) {
            is ChangeCode -> changeCode(viewEvent.value)
            is NextClick -> viewAction = OpenMainScreen()
        }
    }

    private fun changeCode(code: String) {
        viewState = viewState.copy(code = code, isButtonEnabled = code.isNotBlank())
    }
}