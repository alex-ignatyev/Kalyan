package screens.main.profile

sealed class ProfileEvent {
    class InitProfileScreen : ProfileEvent()
}

data class ProfileState(
    val isLoading: Boolean = true,
    val name: String = "Test Name"
)

sealed class ProfileAction
