package screens.main.tobacco.tobacco_feed

import com.adeo.kviewmodel.BaseSharedViewModel
import data.SettingsDataSource
import domain.repository.RatingRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.tobacco.tobacco_feed.TobaccoFeedAction.OpenAdminAddTobaccoScreen
import screens.main.tobacco.tobacco_feed.TobaccoFeedAction.OpenTobaccoInfoScreen
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.AddTobaccoClick
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.ClearActions
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.InitTobaccoFeedScreen
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.OnTobaccoClick
import utils.answer.onFailure
import utils.answer.onSuccess

class TobaccoFeedModel : KoinComponent, BaseSharedViewModel<TobaccoFeedState, TobaccoFeedAction, TobaccoFeedEvent>(
    initialState = TobaccoFeedState()
) {

    private val repository: RatingRepository by inject()
    private val settings: SettingsDataSource by inject()

    override fun obtainEvent(viewEvent: TobaccoFeedEvent) {
        when (viewEvent) {
            is InitTobaccoFeedScreen -> fetchData()
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
