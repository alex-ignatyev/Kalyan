package ui.themes.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ui.themes.KalyanTheme

@Composable
internal fun KalyanButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = KalyanTheme.colors.generalColor,
    shape: Shape = RoundedCornerShape(8.dp),
    onClick: () -> Unit,
    text: String? = null,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {}
) {
    Button(
        modifier = modifier.height(48.dp).fillMaxWidth().padding(horizontal = 16.dp),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = backgroundColor
        )
    ) {
        text?.let {
            Text(
                text = it,
                style = KalyanTheme.typography.body,
                color = Color.White
            )
        } ?: content.invoke(this)
    }
}
