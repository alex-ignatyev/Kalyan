package ui.themes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.themes.KalyanTheme

//TODO Добработать тулбар на элевейшн и TopAppBar
@Composable
fun KalyanToolbar(title: String = "", onClick: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
        Image(
            imageVector = Filled.ArrowBack,
            title,
            colorFilter = ColorFilter.tint(KalyanTheme.colors.primaryText),
            modifier = Modifier.padding(start = 16.dp).align(CenterStart).clickable {
                onClick.invoke()
            }
        )
        Text(
            text = title,
            style = KalyanTheme.typography.header,
            color = KalyanTheme.colors.primaryText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}