package screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kalyan.shared.AppRes
import screens.main.MainEvent.TestClick
import ui.themes.KalyanTheme
import ui.themes.components.KalyanButton

@Composable
fun MainView(state: MainState, obtainEvent: (MainEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(KalyanTheme.colors.secondaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "Main",
            fontSize = 32.sp,
            color = KalyanTheme.colors.primaryText,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )

        KalyanButton(
            text = AppRes.string.action_send,
            onClick = {
                obtainEvent(TestClick())
            })

        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(state.tobaccos) {
                TobaccoView(it)
            }
        }
    }
}