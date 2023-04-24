package screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import screens.main.MainEvent.TestCreate
import ui.themes.components.KalyanButton

@Composable
fun MainView(state: MainState, obtainEvent: (MainEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(16.dp)) {
            items(state.tobaccos) {
                TobaccoView(it)
            }
        }

        KalyanButton(onClick = {
            obtainEvent.invoke(TestCreate())
        })
    }
}
