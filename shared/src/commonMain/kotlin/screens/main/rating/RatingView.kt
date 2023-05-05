package screens.main.rating

import androidx.compose.foundation.layout.fillMaxWidth
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
import di.LocalPlatform
import screens.main.rating.view.TobaccoView
import ui.KalyanTheme

@Composable
fun RatingView(state: RatingState, obtainEvent: (RatingEvent) -> Unit) {
    val platformProvider = LocalPlatform.current

    //modifier = Modifier.fillMaxSize().padding(top = if (platformProvider == iOS) 32.dp else 16.dp),


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

                    })
            }
        }) {
        LazyColumn(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            itemsIndexed(state.data) { index, item ->
                TobaccoView(item, index + 1)
            }
        }
    }
}
