package screens.old.daily

import com.adeo.kviewmodel.BaseSharedViewModel
import com.soywiz.klock.DateTime
import com.soywiz.klock.days
import data.features.daily.DailyRepository
import data.features.medication.MedicationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import screens.old.daily.models.DailyAction
import screens.old.daily.models.DailyEvent
import screens.old.daily.models.DailyViewState
import screens.old.daily.views.HabitCardItemModel

class DailyViewModel : KoinComponent, BaseSharedViewModel<DailyViewState, DailyAction, DailyEvent>(
    initialState = DailyViewState.Loading
) {

    private val dailyRepository: DailyRepository by inject()
    private val medicationRepository: MedicationRepository by inject()

    private var currentDate: DateTime = DateTime.now()

    override fun obtainEvent(event: DailyEvent) {
        if (event is DailyEvent.CloseAction) {
            viewAction = null
        }

        when (viewState) {
            is DailyViewState.Loading -> reduce(event, viewState as DailyViewState.Loading)
            is DailyViewState.Display -> reduce(event, viewState as DailyViewState.Display)
            is DailyViewState.Error -> reduce(event, viewState as DailyViewState.Error)
            is DailyViewState.NoItems -> reduce(event, viewState as DailyViewState.NoItems)
        }
    }

    private fun reduce(event: DailyEvent, currentState: DailyViewState.Loading) {
        when (event) {
            DailyEvent.EnterScreen -> fetchHabitForDate()
            else -> {}
        }
    }

    private fun reduce(event: DailyEvent, currentState: DailyViewState.NoItems) {
        when (event) {
            DailyEvent.ReloadScreen -> fetchHabitForDate(true)
            DailyEvent.EnterScreen -> fetchHabitForDate()
            else -> {}
        }
    }

    private fun reduce(event: DailyEvent, currentState: DailyViewState.Display) {
        when (event) {
            DailyEvent.EnterScreen -> fetchHabitForDate()
            DailyEvent.NextDayClicked -> performNextClick(currentState.hasNextDay)
            DailyEvent.PreviousDayClicked -> performPreviousClick()
            is DailyEvent.OnHabitClick -> performHabbitClick(
                hasNextDay = currentState.hasNextDay,
                habbitId = event.habitId,
                newValue = event.newValue
            )

            else -> {}
        }
    }

    private fun reduce(event: DailyEvent, currentState: DailyViewState.Error) {
        when (event) {
            DailyEvent.ReloadScreen -> fetchHabitForDate(needsToRefresh = true)
            else -> {}
        }
    }

    private fun performHabbitClick(hasNextDay: Boolean, habbitId: Long, newValue: Boolean) {
        viewModelScope.launch {
            dailyRepository.addOrUpdate(
                date = currentDate.format("yyyy-MM-dd"),
                habitId = habbitId,
                value = newValue
            )

            withContext(Dispatchers.Main) {
                fetchHabitForDate()
            }
        }
    }

    private fun performNextClick(hasNextDay: Boolean) {
        currentDate = currentDate.plus(1.0f.days)
        fetchHabitForDate()
    }

    private fun performPreviousClick() {
        currentDate = currentDate.minus(1.0f.days)
        fetchHabitForDate()
    }

    private fun getTitleForADay(): String {
        val now = DateTime.now()

        val difference = now.minus(currentDate)

        return when (difference.days) {
            0.0 -> "Today"
            1.0 -> "Yesterday"
            else -> "${currentDate.dayOfMonth} ${
                currentDate.month.name.take(3).toLowerCase().capitalize()
            }"
        }
    }

    private fun fetchHabitForDate(needsToRefresh: Boolean = false) {
        if (needsToRefresh) {
            viewState = DailyViewState.Loading
        }

        viewModelScope.launch {
            try {
                val currentMedications = medicationRepository.fetchCurrentMedications()

                if (currentMedications.isEmpty()) {
                    viewState = DailyViewState.NoItems
                } else {
                    val diaryResponse = dailyRepository.fetchDiary()

                    val dailyActivities = diaryResponse.firstOrNull {
                        val currentDate = currentDate
                        it.date == currentDate.format("yyyy-MM-dd")
                    }

                    val medicationCardItems: List<HabitCardItemModel> = currentMedications.map { medication ->
                        HabitCardItemModel(
                            habitId = medication.itemId,
                            title = medication.title,
                            isChecked = if (dailyActivities != null) {
                                val dailyItem =
                                    dailyActivities.habits.firstOrNull { it.habbitId == medication.itemId }
                                dailyItem?.value ?: false
                            } else {
                                false
                            }
                        )
                    }

                    val cardItems = medicationCardItems

                    viewState = DailyViewState.Display(
                        items = cardItems,
                        hasNextDay = calculateHasNextDay(),
                        title = getTitleForADay()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState = DailyViewState.Error
            }
        }
    }

    private fun calculateHasNextDay(): Boolean {
        return DateTime.now().minus(currentDate).days > 1.0
    }
}