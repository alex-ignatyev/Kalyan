package ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.KalyanTheme

@Composable
internal fun KalyanDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier.fillMaxWidth(),
        color = KalyanTheme.colors.secondaryBackground
    )
}
