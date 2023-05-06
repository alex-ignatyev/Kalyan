package ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ui.KalyanTheme
import ui.components.TextFieldType.Password

@Composable
fun KalyanTextField(
    value: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    fieldType: TextFieldType = TextFieldType.Text,
    endIcon: @Composable () -> Unit = {},
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        isError = isError,
        enabled = enabled,
        placeholder = { Text(text = placeholder, color = KalyanTheme.colors.secondaryText) },
        visualTransformation = if (fieldType is Password && fieldType.passwordState) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = endIcon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = KalyanTheme.colors.secondaryBackground,
            unfocusedBorderColor = KalyanTheme.colors.secondaryBackground,
            disabledBorderColor = KalyanTheme.colors.secondaryBackground,
            errorBorderColor = KalyanTheme.colors.errorColor,
            backgroundColor = KalyanTheme.colors.secondaryBackground,
            textColor = KalyanTheme.colors.primaryText,
            cursorColor = KalyanTheme.colors.controlColor
        )
    )
}

sealed interface TextFieldType {
    object Text : TextFieldType
    data class Password(val passwordState: Boolean) : TextFieldType
}