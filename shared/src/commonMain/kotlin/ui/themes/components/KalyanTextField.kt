package ui.themes.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.themes.KalyanTheme

@Composable
fun KalyanTextField(value: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth().height(48.dp),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = KalyanTheme.colors.secondaryBackground,
            unfocusedBorderColor = KalyanTheme.colors.secondaryBackground,
            disabledBorderColor = KalyanTheme.colors.secondaryBackground,
            errorBorderColor = KalyanTheme.colors.secondaryBackground,
            backgroundColor = KalyanTheme.colors.secondaryBackground,
            textColor = KalyanTheme.colors.primaryText,
            cursorColor = KalyanTheme.colors.controlColor
        )
    )
}