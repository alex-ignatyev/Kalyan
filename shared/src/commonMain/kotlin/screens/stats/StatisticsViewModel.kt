package screens.stats

import com.adeo.kviewmodel.BaseSharedViewModel
import com.soywiz.klock.DateTime
import data.features.daily.DailyRepository
import data.features.medication.MedicationRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.stats.models.StatsAction
import screens.stats.models.StatsEvent
import screens.stats.models.StatsViewState
import screens.stats.views.StatisticCellModel
import utils.getValueOrNull

class StatisticsViewModel : KoinComponent, BaseSharedViewModel<StatsViewState, StatsAction, StatsEvent>(
    initialState = StatsViewState()
) {

    private val medicationRepository: MedicationRepository by inject()
    private val dailyRepository: DailyRepository by inject()

    init {
        loadActivities()
    }

    override fun obtainEvent(viewEvent: StatsEvent) {
        when (viewEvent) {
            StatsEvent.ReloadScreen -> loadActivities()
        }
    }

    private fun loadActivities() {
        viewModelScope.launch {
            val medicationStatistics = fetchActiveMedication()

            viewState = viewState.copy(activeProgress = medicationStatistics)
        }
    }

    private suspend fun fetchActiveMedication(): List<StatisticCellModel> {
        val medicationList = medicationRepository.fetchCurrentMedications()
        val diary = dailyRepository.fetchDiary()

        return medicationList.map { medication ->
            val filtered = diary.filter { entry ->
                val startDate = getValueOrNull(medication.startDate) ?: return@filter false
                val endDate = getValueOrNull(medication.endDate) ?: return@filter false
                val entryDate = DateTime.fromString(entry.date)

                val startComparable = startDate.compareTo(entryDate)
                val endComparable = endDate.compareTo(entryDate)

                startComparable == 0 && endComparable == 1
            }

            val startDate = getValueOrNull(medication.startDate)
            val endDate = getValueOrNull(medication.endDate)
            val diff = startDate?.let { endDate?.minus(it)?.days } ?: 0.0

            StatisticCellModel(
                title = medication.title,
                activeDayList = listOf(true, true, true, false, false),
                duration = diff.toInt().toString(),
                fact = filtered.size.toString(),
                percentage = filtered.size.toFloat() / diff.toFloat(),
                isPeriodic = false
            )
        }
    }
}