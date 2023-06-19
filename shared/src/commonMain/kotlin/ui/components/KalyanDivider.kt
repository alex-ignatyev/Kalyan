package ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import di.Platform.iOS
import di.currentPlatform
import ui.components.android.AndroidDivider
import ui.components.ios.IosDivider

@Composable
internal fun KalyanDivider(modifier: Modifier = Modifier) {
    when (currentPlatform) {
        iOS -> IosDivider(modifier)
        else -> AndroidDivider(modifier)
    }
}
