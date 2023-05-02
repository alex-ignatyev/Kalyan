package screens.main.rating

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.RatingRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.rating.RatingEvent.InitRatingScreen
import utils.answer.onFailure
import utils.answer.onSuccess

class RatingViewModel : KoinComponent, BaseSharedViewModel<RatingState, RatingAction, RatingEvent>(
    initialState = RatingState()
) {

    private val repository: RatingRepository by inject()

    override fun obtainEvent(viewEvent: RatingEvent) {
        when (viewEvent) {
            is InitRatingScreen -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            repository.getTobaccoFeed().onSuccess {
                viewState = viewState.copy(data = it)
            }.onFailure {
                //TODO Show error
            }
        }
    }
}
