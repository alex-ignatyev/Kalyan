package screens.old.add_name

import com.adeo.kviewmodel.BaseSharedViewModel
import screens.old.add_name.models.MedicationAddNameAction
import screens.old.add_name.models.MedicationAddNameEvent
import screens.old.add_name.models.MedicationAddNameViewState

class MedicationAddNameViewModel: BaseSharedViewModel<MedicationAddNameViewState, MedicationAddNameAction, MedicationAddNameEvent>(
    initialState = MedicationAddNameViewState()
) {

    override fun obtainEvent(viewEvent: MedicationAddNameEvent) {
        when(viewEvent) {
            is MedicationAddNameEvent.ChangeName -> {
                viewState = viewState.copy(name = viewEvent.value, isNext = viewEvent.value.isNotBlank())
            }
            MedicationAddNameEvent.NextClicked -> viewAction = MedicationAddNameAction.NextClicked
            MedicationAddNameEvent.ActionInvoked -> viewAction = null
        }
    }
}