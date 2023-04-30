package screens.splash

sealed class SplashEvent {
    class InitSplashScreen : SplashEvent()
}

class SplashState()

sealed class SplashAction {
    data class OpenFlow(val flow: String) : SplashAction()
}
