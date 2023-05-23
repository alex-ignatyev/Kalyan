package screens.main.tobacco_info

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.RatingRepository
import kotlin.random.Random
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.tobacco_info.TobaccoInfoAction.OpenVoteBottomSheet
import screens.main.tobacco_info.TobaccoInfoAction.ReturnBack
import screens.main.tobacco_info.TobaccoInfoEvent.ChangeAromaSlider
import screens.main.tobacco_info.TobaccoInfoEvent.ChangeSmokinessSlider
import screens.main.tobacco_info.TobaccoInfoEvent.ChangeStrengthSlider
import screens.main.tobacco_info.TobaccoInfoEvent.ChangeTasteSlider
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
            is ChangeStrengthSlider -> changeStrengthSlider(viewEvent.value)
            is ChangeSmokinessSlider -> changeSmokinessSlider(viewEvent.value)
            is ChangeAromaSlider -> changeAromaSlider(viewEvent.value)
            is ChangeTasteSlider -> changeTasteSlider(viewEvent.value)
            is VoteForTobacco -> openVoteBottomSheet()
            is OnBackClick -> returnBack()
            is ClearActions -> clearActions()
        }
    }

    private fun fetchData(tobaccoId: String) {
        this.tobaccoId = tobaccoId
        viewModelScope.launch {
            repo.getTobaccoInfo(tobaccoId).onSuccess {
                viewState = viewState.copy(
                    isLoading = false,

                    image = it.image,

                    taste = it.taste,
                    company = it.company,
                    line = it.line,
                    strengthByCompany = it.strengthByCompany,

                    strengthByUsers = it.strengthByUsers,
                    smokinessByUsers = it.smokinessByUsers,
                    aromaByUsers = it.aromaByUsers,
                    ratingByUsers = it.ratingByUsers,
                    tastePowerByUsers = it.tastePowerByUsers,

                    strengthByUser = it.strengthByUser,
                    smokinessByUser = it.smokinessByUser,
                    aromaByUser = it.aromaByUser,
                    tastePowerByUser = it.tastePowerByUser,
                    ratingByUser = it.ratingByUser,

                    votes = it.votes,
                )
            }.onFailure {
                viewState = viewState.copy(isLoading = false, error = "Ошибка")
            }

        }
    }

    private fun openVoteBottomSheet() {
        viewAction = OpenVoteBottomSheet()
    }

    private fun changeStrengthSlider(value: Float) {
        viewState = viewState.copy(strengthSlider = value)
    }

    private fun changeSmokinessSlider(value: Float) {
        viewState = viewState.copy(smokinessSlider = value)
    }

    private fun changeAromaSlider(value: Float) {
        viewState = viewState.copy(aromaSlider = value)
    }

    private fun changeTasteSlider(value: Float) {
        viewState = viewState.copy(tasteSlider = value)
    }

    private fun voteForTobacco() {
        viewModelScope.launch {
            repo.postTobaccoVote(
                tobaccoId = tobaccoId,
                strength = Random.nextInt(1, 10),
                smokiness = Random.nextInt(1, 10),
                aroma = Random.nextInt(1, 10),
                tastePower = Random.nextInt(1, 10),
                rating = Random.nextInt(1, 10)
            )
        }
    }

    private fun returnBack() {
        viewAction = ReturnBack()
    }

    private fun clearActions() {
        viewAction = null
    }
}
