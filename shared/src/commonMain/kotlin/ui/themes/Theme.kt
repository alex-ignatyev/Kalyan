package ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
internal fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) paletteDark else paletteLight

    val typography = KalyanTypography(
        header = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        toolbar = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        ),
        caption = TextStyle(
            fontSize = 12.sp
        )
    )

    CompositionLocalProvider(
        LocalKalyanColors provides colors,
        LocalKalyanTypography provides typography,
        content = content
    )
}
