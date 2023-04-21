package model

import ui.themes.KalyanCorners
import ui.themes.KalyanSize
import ui.themes.KalyanStyle

data class SettingsBundle(
    val isDarkMode: Boolean,
    val textSize: KalyanSize,
    val paddingSize: KalyanSize,
    val cornerStyle: KalyanCorners,
    val style: KalyanStyle
)