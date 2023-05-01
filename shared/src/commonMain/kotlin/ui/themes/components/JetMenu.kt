package ui.themes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.themes.KalyanTheme

@Composable
internal fun JetMenu(
    title: String,
    value: String,
    onMenuClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(KalyanTheme.colors.primaryBackground)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .clickable { onMenuClicked.invoke() }
                .background(KalyanTheme.colors.primaryBackground),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = title,
                style = KalyanTheme.typography.body,
                color = KalyanTheme.colors.primaryText
            )

            Text(
                text = value,
                style = KalyanTheme.typography.body,
                color = KalyanTheme.colors.secondaryText
            )
        }

        Divider(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.BottomStart),
            thickness = 0.5.dp,
            color = KalyanTheme.colors.secondaryText.copy(
                alpha = 0.3f
            )
        )
    }

}