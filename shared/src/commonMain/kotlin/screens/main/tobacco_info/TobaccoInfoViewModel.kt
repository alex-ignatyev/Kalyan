package screens.main.tobacco_info

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.RatingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.tobacco.TobaccoVoteRequest.VoteType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.tobacco_info.TobaccoInfoAction.ReturnBack
import screens.main.tobacco_info.TobaccoInfoEvent.ClearActions
import screens.main.tobacco_info.TobaccoInfoEvent.InitTobaccoInfoScreen
import screens.main.tobacco_info.TobaccoInfoEvent.OnBackClick
import screens.main.tobacco_info.TobaccoInfoEvent.VoteForTobacco
import utils.answer.onFailure
import utils.answer.onSuccess

class TobaccoInfoViewModel : KoinComponent, BaseSharedViewModel<TobaccoInfoState, TobaccoInfoAction, TobaccoInfoEvent>(
    initialState = TobaccoInfoState()
) {

    private val repo: RatingRepository by inject()
    private var tobaccoId: String = ""

    override fun obtainEvent(viewEvent: TobaccoInfoEvent) {
        when (viewEvent) {
            is InitTobaccoInfoScreen -> fetchData(viewEvent.tobaccoId)
            is VoteForTobacco -> voteForTobacco(viewEvent.type, viewEvent.value)
            is OnBackClick -> returnBack()
            is ClearActions -> clearActions()
        }
    }

    private fun fetchData(tobaccoId: String) {
        this.tobaccoId = tobaccoId
        viewModelScope.launch {
            delay(5000L)
            repo.getTobaccoInfo(tobaccoId).onSuccess {
                viewState = viewState.copy(
                    isLoading = false,

                    image = it.image ?: "",

                    taste = it.taste ?: "",
                    company = it.company ?: "",
                    line = it.line ?: "",
                    strengthByCompany = it.strength ?: 0,

                    ratingByUsers = it.ratingByUsers ?: 0f,
                    strengthByUsers = it.strengthByUsers ?: 0f,
                    smokinessByUsers = it.smokinessByUsers ?: 0f,
                    aromaByUsers = it.aromaByUsers ?: 0f,
                    tasteByUsers = it.tastePowerByUsers ?: 0f,

                    ratingByUser = it.ratingByUser ?: 0,
                    strengthByUser = it.strengthByUser ?: 0,
                    smokinessByUser = it.smokinessByUser ?: 0,
                    aromaByUser = it.aromaByUser ?: 0,
                    tasteByUser = it.tasteByUser ?: 0,

                    votes = it.votes ?: 0L,
                )
            }.onFailure {
                viewState = viewState.copy(isLoading = false, error = "Ошибка")
            }
        }
    }

    private fun voteForTobacco(type: VoteType, value: Long) {
        viewModelScope.launch {
            repo.postTobaccoVote(
                tobaccoId = tobaccoId,
                type = type,
                value = value
            ).onSuccess {
                fetchData(tobaccoId)
            }
        }
    }

    private fun returnBack() {
        viewAction = ReturnBack()
    }

    private fun clearActions() {
        viewAction = null
    }
}
