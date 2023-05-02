package screens.main.rating

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import di.LocalPlatform
import di.Platform.iOS
import screens.main.rating.view.TobaccoView

@Composable
fun RatingView(state: RatingState, obtainEvent: (RatingEvent) -> Unit) {
    val platformProvider = LocalPlatform.current

    Column(
        modifier = Modifier.fillMaxSize().padding(top = if (platformProvider == iOS) 32.dp else 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            itemsIndexed(state.data) { index, item ->
                TobaccoView(item, index + 1)
            }
        }
    }
}
