package screens.main.admin_add_tabacco

import model.domain.Company

sealed class AdminAddTobaccoEvent {
    class InitAdminAddTobaccoScreen : AdminAddTobaccoEvent()
    data class ChangeManual(val isChecked: Boolean) : AdminAddTobaccoEvent()
    data class ChangeCompany(val value: String) : AdminAddTobaccoEvent()
    data class ChangeTaste(val value: String) : AdminAddTobaccoEvent()
    data class ChangeLine(val value: String) : AdminAddTobaccoEvent()
    data class ChangeStrengthByCompany(val value: String) : AdminAddTobaccoEvent()
    class OnCompanyClick : AdminAddTobaccoEvent()
    class OnLineClick(val lines: List<String>) : AdminAddTobaccoEvent()
    class AddTobaccoClick : AdminAddTobaccoEvent()
    class OnBackClick : AdminAddTobaccoEvent()
    class ClearActions : AdminAddTobaccoEvent()
}

//TODO Создать модель для всех полей
data class AdminAddTobaccoState(
    val isLoading: Boolean = true,
    val isManual: Boolean = false,
    val company: String = "",
    val taste: String = "",
    val line: String = "",
    val strength: String = "",
    val companies: List<Company> = emptyList(),
    val isButtonEnabled: Boolean = false,
    val error: String = ""
)

sealed class AdminAddTobaccoAction {
    class ReturnToPreviousScreen : AdminAddTobaccoAction()
    class OpenCompanySheet : AdminAddTobaccoAction()
    data class OpenLineSheet(val lines: List<String>) : AdminAddTobaccoAction()
}
