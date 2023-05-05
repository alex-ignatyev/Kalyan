package screens.splash

import com.adeo.kviewmodel.BaseSharedViewModel
import data.SettingsDataSource
import domain.repository.AuthRepository
import kotlinx.coroutines.launch
import navigation.FLOW_MAIN
import navigation.SCREEN_LOGIN
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.splash.SplashAction.OpenFlow
import screens.splash.SplashEvent.InitSplashScreen
import utils.answer.onFailure
import utils.answer.onSuccess

class SplashViewModel : KoinComponent, BaseSharedViewModel<SplashState, SplashAction, SplashEvent>(
    initialState = SplashState()
) {

    val authRepository: AuthRepository by inject()
    val settings: SettingsDataSource by inject()

    override fun obtainEvent(viewEvent: SplashEvent) {
        when (viewEvent) {
            is InitSplashScreen -> fetchAuthorization()
        }
    }

    private fun fetchAuthorization() {
        viewModelScope.launch {
            val token = settings.getToken()
            if (token.isBlank()) {
                viewAction = OpenFlow(SCREEN_LOGIN)
            } else {
                authRepository.authorize().onSuccess {
                    viewAction = OpenFlow(FLOW_MAIN)
                }.onFailure {
                    settings.clear()
                    viewAction = OpenFlow(SCREEN_LOGIN)
                }
            }
        }
    }
}
