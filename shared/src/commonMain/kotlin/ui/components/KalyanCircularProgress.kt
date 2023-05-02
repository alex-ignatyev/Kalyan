package ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun KalyanCircularProgress(
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    CircularProgressIndicator(
        modifier = modifier.size(32.dp),
        strokeWidth = 2.dp,
        color = color
    )
}
