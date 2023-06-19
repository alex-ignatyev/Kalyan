package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kalyan.shared.strings.AppResStrings
import ui.KalyanTheme

@Composable
fun ErrorScreen(onRepeatClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(KalyanTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = AppResStrings.error_something_went_wrong_screen_title,
                style = KalyanTheme.typography.header,
                textAlign = TextAlign.Center
            )
            Text(
                text = AppResStrings.error_something_went_wrong_screen_hint,
                style = KalyanTheme.typography.hint,
                textAlign = TextAlign.Center
            )
            KalyanButton(text = "Повторить", modifier = Modifier.padding(top = 32.dp)) {
                onRepeatClick()
            }
        }
    }
}