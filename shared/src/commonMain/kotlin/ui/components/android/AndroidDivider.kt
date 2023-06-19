package ui.components.android

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.KalyanTheme

@Composable
internal fun AndroidDivider(
    modifier: Modifier = Modifier,
) {
    Divider(
        modifier = modifier.fillMaxWidth(),
        color = KalyanTheme.colors.surfaceVariant
    )
}
