package screens.main.admin_add_tabacco

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.AdminRepository
import kotlinx.coroutines.launch
import model.admin.AdminAddTobaccoRequest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.admin_add_tabacco.AdminAddTobaccoAction.ReturnToPreviousScreen
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.AddTobaccoClick
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeCompany
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeLine
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeStrengthByCompany
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeTaste
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ClearActions
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.InitAdminAddTobaccoScreen
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.OnBackClick
import utils.EMPTY
import utils.answer.onFailure
import utils.answer.onSuccess

class AdminAddTobaccoViewModel : KoinComponent,
    BaseSharedViewModel<AdminAddTobaccoState, AdminAddTobaccoAction, AdminAddTobaccoEvent>(
        initialState = AdminAddTobaccoState()
    ) {

    private val repo: AdminRepository by inject()

    override fun obtainEvent(viewEvent: AdminAddTobaccoEvent) {
        when (viewEvent) {
            is InitAdminAddTobaccoScreen -> fetchData()
            is ChangeCompany -> changeCompany(viewEvent.value)
            is ChangeTaste -> changeTaste(viewEvent.value)
            is ChangeLine -> changeLine(viewEvent.value)
            is ChangeStrengthByCompany -> changeStrengthByCompany(viewEvent.value)
            is AddTobaccoClick -> addTobacco()
            is OnBackClick -> returnToPreviousScreen()
            is ClearActions -> clearActions()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            repo.getCompanies().onSuccess {
                viewState = viewState.copy(isLoading = false, companies = it)
            }.onFailure {
                viewState = viewState.copy(isLoading = false, error = it.message)
            }
        }
    }

    private fun changeCompany(company: String) {
        viewState = viewState.copy(company = company, line = EMPTY, error = EMPTY)
    }

    private fun changeTaste(taste: String) {
        viewState = viewState.copy(taste = taste, error = EMPTY)
    }

    private fun changeLine(line: String) {
        viewState = viewState.copy(line = line, error = EMPTY)
    }

    private fun changeStrengthByCompany(strength: String) {
        viewState = viewState.copy(strengthByCompany = strength, error = EMPTY)
    }

    private fun addTobacco() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            val request = AdminAddTobaccoRequest(
                company = viewState.company,
                taste = viewState.taste,
                line = viewState.line,
                strengthByCompany = if (viewState.strengthByCompany.isBlank()) 0 else viewState.strengthByCompany.toInt()
            )
            repo.addTobacco(request).onSuccess {
                viewAction = ReturnToPreviousScreen()
            }.onFailure {
                viewState = viewState.copy(isLoading = false, error = it.message)
            }
        }
    }

    private fun returnToPreviousScreen() {
        viewAction = ReturnToPreviousScreen()
    }

    private fun clearActions() {
        viewAction = null
        viewState = viewState.copy(isLoading = false, error = EMPTY)
    }
}
