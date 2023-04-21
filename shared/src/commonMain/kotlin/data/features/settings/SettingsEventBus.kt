package data.features.settings

import androidx.compose.runtime.staticCompositionLocalOf
import model.SettingsBundle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ui.themes.KalyanCorners
import ui.themes.KalyanCorners.Rounded
import ui.themes.KalyanSize
import ui.themes.KalyanSize.Medium
import ui.themes.KalyanStyle
import ui.themes.KalyanStyle.Orange

class SettingsEventBus {

    private val _currentSettings: MutableStateFlow<SettingsBundle> = MutableStateFlow(
        SettingsBundle(
            isDarkMode = true,
            cornerStyle = Rounded,
            style = Orange,
            textSize = Medium,
            paddingSize = Medium
        )
    )
    val currentSettings: StateFlow<SettingsBundle> = _currentSettings

    fun updateDarkMode(isDarkMode: Boolean) {
        _currentSettings.value = _currentSettings.value.copy(isDarkMode = isDarkMode)
    }

    fun updateCornerStyle(corners: KalyanCorners) {
        _currentSettings.value = _currentSettings.value.copy(cornerStyle = corners)
    }

    fun updateStyle(style: KalyanStyle) {
        _currentSettings.value = _currentSettings.value.copy(style = style)
    }

    fun updateTextSize(textSize: KalyanSize) {
        _currentSettings.value = _currentSettings.value.copy(textSize = textSize)
    }

    fun updatePaddingSize(paddingSize: KalyanSize) {
        _currentSettings.value = _currentSettings.value.copy(paddingSize = paddingSize)
    }
}

internal val LocalSettingsEventBus = staticCompositionLocalOf {
    SettingsEventBus()
}