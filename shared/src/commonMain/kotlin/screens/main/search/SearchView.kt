package screens.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kalyan.shared.strings.AppResStrings
import com.moriatsushi.insetsx.safeArea
import ui.KalyanTheme

@Composable
internal fun SearchView(viewState: SearchState, obtainEvent: (SearchEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(KalyanTheme.colors.primaryBackground)
            .windowInsetsPadding(WindowInsets.safeArea)
    ) {
        Text(
            text = AppResStrings.title_search,
            style = KalyanTheme.typography.header,
            color = KalyanTheme.colors.primaryText
        )
    }
}
