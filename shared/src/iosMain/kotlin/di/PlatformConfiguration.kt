package di

actual class PlatformConfiguration {
    actual val appName: String
        get() = "Kalyan"

    actual val platform: Platform
        get() = Platform.iOS
}
