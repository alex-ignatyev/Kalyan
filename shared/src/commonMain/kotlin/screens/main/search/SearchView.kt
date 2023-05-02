package screens.main.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.kalyan.shared.AppRes
import com.kalyan.shared.strings.AppResStrings
import ui.KalyanTheme

@Composable
internal fun SearchView(viewState: SearchState, obtainEvent: (SearchEvent) -> Unit) {
    Column {
        Text(
            text = AppResStrings.title_search,
            style = KalyanTheme.typography.header,
            color = KalyanTheme.colors.primaryText
        )
    }
}
