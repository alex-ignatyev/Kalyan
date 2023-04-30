package screens.main.rating

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.MainRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.rating.RatingEvent.TestCreate
import utils.answer.onFailure
import utils.answer.onSuccess

class RatingViewModel : KoinComponent, BaseSharedViewModel<RatingState, RatingAction, RatingEvent>(
    initialState = RatingState()
) {

    private val repository: MainRepository by inject()

    init {
        testClick()
    }

    override fun obtainEvent(viewEvent: RatingEvent) {
        when (viewEvent) {
            is TestCreate -> test()
        }
    }

    private fun testClick() {
        viewModelScope.launch {
            repository.getTobaccos().onSuccess {
                viewState = viewState.copy(tobaccos = it.tobaccos ?: listOf())
            }.onFailure {

            }
        }
    }

    private fun test() {
        viewModelScope.launch {
            repository.createTobacco().onSuccess {
                testClick()
            }
        }
    }
}
