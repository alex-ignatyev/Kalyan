package ui.themes.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.themes.KalyanTheme

@Composable
internal fun KalyanDivider() {
    Divider(
        modifier = Modifier.fillMaxWidth().height(1.dp).padding(horizontal = 16.dp),
        thickness = 0.5.dp,
        color = KalyanTheme.colors.controlColor.copy(alpha = 0.1f)
    )
}
