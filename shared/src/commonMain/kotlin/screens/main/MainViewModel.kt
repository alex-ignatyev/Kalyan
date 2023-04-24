package screens.main

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.MainRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.MainEvent.TestCreate
import utils.answer.onFailure
import utils.answer.onSuccess

class MainViewModel : KoinComponent, BaseSharedViewModel<MainState, MainAction, MainEvent>(
    initialState = MainState()
) {

    private val repository: MainRepository by inject()

    init {
        testClick()
    }

    override fun obtainEvent(viewEvent: MainEvent) {
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
