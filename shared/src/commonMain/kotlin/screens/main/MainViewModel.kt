package screens.main

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.MainRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.MainEvent.TestClick
import utils.answer.onFailure
import utils.answer.onSuccess

class MainViewModel : KoinComponent, BaseSharedViewModel<MainState, MainAction, MainEvent>(
    initialState = MainState()
) {

    private val repository: MainRepository by inject()

    override fun obtainEvent(viewEvent: MainEvent) {
        when (viewEvent) {
            is TestClick -> testClick()
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
}