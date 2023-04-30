package screens.main.profile

import com.adeo.kviewmodel.BaseSharedViewModel
import screens.main.profile.ProfileEvent.InitProfileScreen

class ProfileViewModel : BaseSharedViewModel<ProfileState, ProfileAction, ProfileEvent>(
    initialState = ProfileState()
) {

    override fun obtainEvent(viewEvent: ProfileEvent) {
        when (viewEvent) {
            is InitProfileScreen -> fetchData()
        }
    }

    private fun fetchData() {

    }
}
