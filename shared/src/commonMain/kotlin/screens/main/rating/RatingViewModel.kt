package screens.main.rating

import com.adeo.kviewmodel.BaseSharedViewModel
import data.SettingsDataSource
import domain.repository.RatingRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.rating.RatingAction.OpenAdminAddTobaccoScreen
import screens.main.rating.RatingAction.OpenTobaccoInfoScreen
import screens.main.rating.RatingEvent.AddTobaccoClick
import screens.main.rating.RatingEvent.ClearActions
import screens.main.rating.RatingEvent.InitRatingScreen
import screens.main.rating.RatingEvent.OnTobaccoClick
import utils.answer.onFailure
import utils.answer.onSuccess

class RatingViewModel : KoinComponent, BaseSharedViewModel<RatingState, RatingAction, RatingEvent>(
    initialState = RatingState()
) {

    private val repository: RatingRepository by inject()
    private val settings: SettingsDataSource by inject()

    override fun obtainEvent(viewEvent: RatingEvent) {
        when (viewEvent) {
            is InitRatingScreen -> fetchData()
            is OnTobaccoClick -> openTobaccoInfoScreen(viewEvent.tobaccoId)
            is AddTobaccoClick -> openAdminAddTobaccoScreen()
            is ClearActions -> clearActions()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            viewState = viewState.copy(isAdmin = settings.getAdmin())
            repository.getTobaccoFeed().onSuccess {
                viewState = viewState.copy(data = it, isLoading = false)
            }.onFailure {
                //TODO Show error
            }
        }
    }

    private fun openTobaccoInfoScreen(tobaccoId: String) {
        viewAction = OpenTobaccoInfoScreen(tobaccoId)
    }

    private fun openAdminAddTobaccoScreen() {
        viewAction = OpenAdminAddTobaccoScreen()
    }

    private fun clearActions() {
        viewAction = null
    }
}
