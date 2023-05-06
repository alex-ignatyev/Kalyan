package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class KalyanColors(
    val primaryBackground: Color,
    val secondaryBackground: Color,

    val generalColor: Color,
    val tintColor: Color,

    val buttonDisable: Color,

    val primaryText: Color,
    val secondaryText: Color,

    val controlColor: Color,
    val errorColor: Color,
)

data class KalyanTypography(
    val header: TextStyle,
    val toolbar: TextStyle,
    val body: TextStyle,
    val caption: TextStyle,
    val hint: TextStyle
)

object KalyanTheme {
    internal val colors: KalyanColors
        @Composable
        internal get() = LocalKalyanColors.current

    internal val typography: KalyanTypography
        @Composable
        internal get() = LocalKalyanTypography.current
}

internal val LocalKalyanColors = staticCompositionLocalOf<KalyanColors> { error("No colors provided") }
internal val LocalKalyanTypography = staticCompositionLocalOf<KalyanTypography> { error("No font provided") }
