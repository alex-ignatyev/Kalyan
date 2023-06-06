package screens.main.admin_add_tabacco

import com.adeo.kviewmodel.BaseSharedViewModel
import domain.repository.AdminRepository
import kotlinx.coroutines.launch
import model.data.admin.AdminAddTobaccoRequest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.main.admin_add_tabacco.AdminAddTobaccoAction.OpenCompanySheet
import screens.main.admin_add_tabacco.AdminAddTobaccoAction.OpenLineSheet
import screens.main.admin_add_tabacco.AdminAddTobaccoAction.ReturnToPreviousScreen
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.AddTobaccoClick
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeCompany
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeLine
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeManual
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeStrengthByCompany
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeTaste
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ClearActions
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.InitAdminAddTobaccoScreen
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.OnBackClick
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.OnCompanyClick
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.OnLineClick
import utils.EMPTY
import utils.answer.onFailure
import utils.answer.onSuccess
import utils.areNotEmpty
import utils.toLongOrDef

class AdminAddTobaccoViewModel : KoinComponent,
    BaseSharedViewModel<AdminAddTobaccoState, AdminAddTobaccoAction, AdminAddTobaccoEvent>(
        initialState = AdminAddTobaccoState()
    ) {

    private val repo: AdminRepository by inject()

    override fun obtainEvent(viewEvent: AdminAddTobaccoEvent) {
        when (viewEvent) {
            is InitAdminAddTobaccoScreen -> fetchData()
            is ChangeManual -> changeInputType(viewEvent.isChecked)
            is ChangeCompany -> changeCompany(viewEvent.value)
            is ChangeTaste -> changeTaste(viewEvent.value)
            is ChangeLine -> changeLine(viewEvent.value)
            is ChangeStrengthByCompany -> changeStrengthByCompany(viewEvent.value)
            is AddTobaccoClick -> addTobacco()
            is OnBackClick -> returnToPreviousScreen()
            is OnCompanyClick -> openCompaniesSheet()
            is OnLineClick -> openLinesSheet(viewEvent.lines)
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

    private fun changeInputType(isChecked: Boolean) {
        viewState = viewState.copy(isManual = isChecked)
    }

    private fun changeCompany(company: String) {
        viewState = viewState.copy(company = company, line = EMPTY, error = EMPTY, isButtonEnabled = isButtonEnabled())
    }

    private fun changeTaste(taste: String) {
        viewState = viewState.copy(taste = taste, error = EMPTY, isButtonEnabled = isButtonEnabled())
    }

    private fun changeLine(line: String) {
        viewState = viewState.copy(line = line, error = EMPTY, isButtonEnabled = isButtonEnabled())
    }

    private fun changeStrengthByCompany(strength: String) {
        viewState = viewState.copy(strength = strength, error = EMPTY, isButtonEnabled = isButtonEnabled())
    }

    private fun openCompaniesSheet() {
        viewAction = OpenCompanySheet()
    }

    private fun openLinesSheet(lines: List<String>) {
        viewAction = OpenLineSheet(lines)
    }

    private fun addTobacco() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            val request = AdminAddTobaccoRequest(
                company = viewState.company,
                taste = viewState.taste,
                line = viewState.line,
                strength = viewState.strength.toLongOrDef()
            )
            repo.addTobacco(request).onSuccess {
                viewAction = ReturnToPreviousScreen()
            }.onFailure {
                viewState = viewState.copy(isLoading = false, error = it.message)
            }
        }
    }

    private fun isButtonEnabled(): Boolean {
        return areNotEmpty(viewState.company, viewState.line) && viewState.strength.toLongOrDef() in 0L..10L
    }

    private fun returnToPreviousScreen() {
        viewAction = ReturnToPreviousScreen()
    }

    private fun clearActions() {
        viewAction = null
        viewState = viewState.copy(isLoading = false, error = EMPTY)
    }
}
