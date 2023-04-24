package ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun MainTheme(
    style: KalyanStyle = KalyanStyle.Purple,
    textSize: KalyanSize = KalyanSize.Medium,
    paddingSize: KalyanSize = KalyanSize.Medium,
    corners: KalyanCorners = KalyanCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                KalyanStyle.Purple -> purpleDarkPalette
                KalyanStyle.Blue -> blueDarkPalette
                KalyanStyle.Orange -> orangeDarkPalette
                KalyanStyle.Red -> redDarkPalette
                KalyanStyle.Green -> greenDarkPalette
            }
        }
        false -> {
            when (style) {
                KalyanStyle.Purple -> purpleLightPalette
                KalyanStyle.Blue -> blueLightPalette
                KalyanStyle.Orange -> orangeLightPalette
                KalyanStyle.Red -> redLightPalette
                KalyanStyle.Green -> greenLightPalette
            }
        }
    }

    val typography = KalyanTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                KalyanSize.Small -> 24.sp
                KalyanSize.Medium -> 28.sp
                KalyanSize.Big -> 32.sp
            },
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                KalyanSize.Small -> 14.sp
                KalyanSize.Medium -> 16.sp
                KalyanSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Normal
        ),
        toolbar = TextStyle(
            fontSize = when (textSize) {
                KalyanSize.Small -> 14.sp
                KalyanSize.Medium -> 16.sp
                KalyanSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Medium
        ),
        caption = TextStyle(
            fontSize = when (textSize) {
                KalyanSize.Small -> 10.sp
                KalyanSize.Medium -> 12.sp
                KalyanSize.Big -> 14.sp
            }
        )
    )

    val shapes = KalyanShape(
        padding = when (paddingSize) {
            KalyanSize.Small -> 12.dp
            KalyanSize.Medium -> 16.dp
            KalyanSize.Big -> 20.dp
        },
        cornersStyle = when (corners) {
            KalyanCorners.Flat -> RoundedCornerShape(0.dp)
            KalyanCorners.Rounded -> RoundedCornerShape(8.dp)
        }
    )

    val images = KalyanImage(
        mainIcon =  null, //if (darkTheme) R.drawable.ic_baseline_mood_24 else R.drawable.ic_baseline_mood_bad_24,
        mainIconDescription = if (darkTheme) "Good Mood" else "Bad Mood"
    )

    CompositionLocalProvider(
        LocalKalyanColors provides colors,
        LocalKalyanTypography provides typography,
        LocalKalyanShape provides shapes,
        LocalKalyanImage provides images,
        content = content
    )
}
