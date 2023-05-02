package screens.main.search

import com.adeo.kviewmodel.BaseSharedViewModel
import org.koin.core.component.KoinComponent
import screens.main.search.SearchEvent.InitSearchScreen

class SearchViewModel : KoinComponent, BaseSharedViewModel<SearchState, SearchAction, SearchEvent>(
    initialState = SearchState()
) {

    override fun obtainEvent(viewEvent: SearchEvent) {
        when (viewEvent) {
            is InitSearchScreen -> fetchData()
        }
    }

    private fun fetchData() {

    }
}
