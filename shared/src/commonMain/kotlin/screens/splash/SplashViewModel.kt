package screens.splash

import com.adeo.kviewmodel.BaseSharedViewModel
import data.SettingsDataSource
import navigation.FLOW_AUTH
import navigation.FLOW_MAIN
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.splash.SplashAction.OpenFlow
import screens.splash.SplashEvent.InitSplashScreen

class SplashViewModel : KoinComponent, BaseSharedViewModel<SplashState, SplashAction, SplashEvent>(
    initialState = SplashState()
) {

    val settings: SettingsDataSource by inject()

    override fun obtainEvent(viewEvent: SplashEvent) {
        when (viewEvent) {
            is InitSplashScreen -> fetchAuthorization()
        }
    }

    private fun fetchAuthorization() {
        val token = settings.getToken()
        val screenFlow = if (token.isBlank()) {
            FLOW_AUTH
        } else {
            FLOW_MAIN
        }

        viewAction = OpenFlow(screenFlow)
    }
}
