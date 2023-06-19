package screens.main.tobacco.tobacco_feed

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.RatingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.tobacco.tobacco_feed.TobaccoFeedAction.OpenTobaccoInfoScreen
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.ClearActions
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.InitTobaccoFeedScreen
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.OnTobaccoClick
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.OnTobaccoSearch
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.RefreshTobaccoFeedScreen
import utils.EMPTY
import utils.answer.onFailure
import utils.answer.onSuccess
import utils.mvi.Action
import utils.mvi.Event

class TobaccoFeedViewModel : KoinComponent, BaseSharedViewModel<TobaccoFeedState, Action, Event>(
    initialState = TobaccoFeedState()
) {

    private val repository: RatingRepository by inject()

    override fun obtainEvent(viewEvent: Event) {
        when (viewEvent) {
            is InitTobaccoFeedScreen -> fetchData()
            is OnTobaccoSearch -> onTobaccoSearch(viewEvent.search)
            is RefreshTobaccoFeedScreen -> refreshData()
            is OnTobaccoClick -> openTobaccoInfoScreen(viewEvent.tobaccoId)
            is ClearActions -> clearActions()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true, isError = false, isRefresh = false)
            delay(2000L)
            repository.getTobaccoFeed(viewState.search).onSuccess {
                viewState = viewState.copy(data = it, isLoading = false)
            }.onFailure {
                viewState = viewState.copy(isError = true)
            }
        }
    }

    private fun onTobaccoSearch(search: String) {
        viewState = viewState.copy(search = search)
        fetchData()
    }

    private fun refreshData() {
        viewState = viewState.copy(isRefresh = true)
        fetchData()
    }

    private fun openTobaccoInfoScreen(tobaccoId: String) {
        viewAction = OpenTobaccoInfoScreen(tobaccoId)
    }

    private fun clearActions() {
        viewAction = null
    }
}
