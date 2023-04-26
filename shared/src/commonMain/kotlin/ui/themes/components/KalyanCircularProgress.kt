package ui.themes.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun KalyanCircularProgress(
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color
    )
}
