package screens.main.rating

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import di.LocalPlatform
import screens.main.rating.RatingEvent.AddTobaccoClick
import screens.main.rating.RatingEvent.OnTobaccoClick
import screens.main.rating.view.TobaccoView
import ui.KalyanTheme

@Composable
fun RatingView(state: RatingState, obtainEvent: (RatingEvent) -> Unit) {
    Scaffold(
        backgroundColor = KalyanTheme.colors.primaryBackground,
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (state.isAdmin) {
                FloatingActionButton(
                    backgroundColor = KalyanTheme.colors.generalColor,
                    content = {
                        Icon(Icons.Default.Add, null, tint = KalyanTheme.colors.primaryBackground)
                    },
                    onClick = {
                        obtainEvent.invoke(AddTobaccoClick())
                    })
            }
        }) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(it),
            contentPadding = PaddingValues(
                all = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(state.data) { index, item ->
                TobaccoView(item, index + 1, Modifier.clickable {
                    obtainEvent.invoke(OnTobaccoClick(item.id))
                })
            }
        }
    }
}
Л