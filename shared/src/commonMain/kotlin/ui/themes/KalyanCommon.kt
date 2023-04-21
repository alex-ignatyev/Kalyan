package ui.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class KalyanColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color,
)

data class KalyanTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
    val caption: TextStyle
)

data class KalyanShape(
    val padding: Dp,
    val cornersStyle: Shape
)

data class KalyanImage(
    val mainIcon: Int?,
    val mainIconDescription: String
)

object KalyanTheme {
    internal val colors: KalyanColors
        @Composable
        internal get() = LocalKalyanColors.current

    internal val typography: KalyanTypography
        @Composable
        internal get() = LocalKalyanTypography.current

    internal val shapes: KalyanShape
        @Composable
        internal get() = LocalKalyanShape.current

    internal val images: KalyanImage
        @Composable
        internal get() = LocalKalyanImage.current
}

internal val LocalKalyanColors = staticCompositionLocalOf<KalyanColors> { error("No colors provided") }
internal val LocalKalyanTypography = staticCompositionLocalOf<KalyanTypography> { error("No font provided") }
internal val LocalKalyanShape = staticCompositionLocalOf<KalyanShape> { error("No shapes provided") }
internal val LocalKalyanImage = staticCompositionLocalOf<KalyanImage> { error("No images provided") }

enum class KalyanStyle {
    Purple, Orange, Blue, Red, Green
}

enum class KalyanSize {
    Small, Medium, Big
}

enum class KalyanCorners {
    Flat, Rounded
}
