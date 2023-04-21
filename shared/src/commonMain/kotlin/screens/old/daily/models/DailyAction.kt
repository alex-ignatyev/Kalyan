package screens.old.daily.models

sealed class DailyAction {
    data class OpenDetail(val itemId: String) : DailyAction()
}