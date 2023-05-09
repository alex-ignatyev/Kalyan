package screens.main.admin_add_tabacco

import model.admin.CompanyResponse

sealed class AdminAddTobaccoEvent {
    class InitAdminAddTobaccoScreen : AdminAddTobaccoEvent()
    data class ChangeCompany(val value: String) : AdminAddTobaccoEvent()
    data class ChangeTaste(val value: String) : AdminAddTobaccoEvent()
    data class ChangeLine(val value: String) : AdminAddTobaccoEvent()
    data class ChangeStrengthByCompany(val value: String) : AdminAddTobaccoEvent()
    class AddTobaccoClick : AdminAddTobaccoEvent()
    class OnBackClick : AdminAddTobaccoEvent()
    class ClearActions : AdminAddTobaccoEvent()
}

data class AdminAddTobaccoState(
    val isLoading: Boolean = true,
    val company: String = "",
    val taste: String = "",
    val line: String = "",
    val strengthByCompany: String = "",
    val companies: List<CompanyResponse> = emptyList(),
    val error: String = ""
)

sealed class AdminAddTobaccoAction {
    class ReturnToPreviousScreen : AdminAddTobaccoAction()
}