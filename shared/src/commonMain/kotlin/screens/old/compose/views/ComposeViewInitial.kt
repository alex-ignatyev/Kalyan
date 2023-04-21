package screens.old.compose.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import screens.old.compose.models.ComposeViewState
import com.kalyan.shared.AppRes
import ui.themes.KalyanTheme

@ExperimentalFoundationApi
@Composable
internal fun ComposeViewInitial(
    modifier: Modifier = Modifier,
    state: ComposeViewState.ViewStateInitial,
    onCheckedChange: (Boolean) -> Unit,
    onTitleChanged: (String) -> Unit,
    onSaveClicked: () -> Unit,
    onCloseClicked: () -> Unit,
    onClearClicked: () -> Unit
) {
    Surface(modifier = modifier.fillMaxSize(), color = KalyanTheme.colors.primaryBackground) {
        Box {
            LazyColumn(
                Modifier.background(KalyanTheme.colors.primaryBackground),
                content = {
                    stickyHeader {
                        Text(
                            modifier = Modifier.padding(
                                horizontal = KalyanTheme.shapes.padding,
                                vertical = KalyanTheme.shapes.padding + 8.dp
                            ),
                            text = AppRes.string.compose_new_record,
                            style = KalyanTheme.typography.heading,
                            color = KalyanTheme.colors.primaryText
                        )
                    }

                    item {
                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            Text(
                                text = AppRes.string.compose_title,
                                style = KalyanTheme.typography.caption,
                                color = KalyanTheme.colors.secondaryText
                            )

                            TextField(
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .fillMaxWidth(),
                                singleLine = true,
                                enabled = !state.isSending,
                                value = state.habitTitle,
                                trailingIcon = {
                                    if (state.habitTitle.isNotBlank()) {
                                        Icon(
                                            modifier = Modifier.clickable { onClearClicked.invoke() },
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = "Close",
                                            tint = KalyanTheme.colors.controlColor
                                        )
                                    }
                                },
                                onValueChange = onTitleChanged,
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = KalyanTheme.colors.primaryBackground,
                                    textColor = KalyanTheme.colors.primaryText,
                                    focusedIndicatorColor = KalyanTheme.colors.tintColor,
                                    disabledIndicatorColor = KalyanTheme.colors.controlColor,
                                    cursorColor = KalyanTheme.colors.tintColor
                                )
                            )
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.padding(end = 16.dp),
                                text = AppRes.string.compose_is_good,
                                style = KalyanTheme.typography.body,
                                color = KalyanTheme.colors.primaryText
                            )

                            Checkbox(
                                checked = state.isGoodHabit,
                                enabled = !state.isSending,
                                onCheckedChange = onCheckedChange,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = KalyanTheme.colors.tintColor,
                                    uncheckedColor = KalyanTheme.colors.secondaryText,
                                    disabledColor = KalyanTheme.colors.tintColor.copy(
                                        alpha = 0.3f
                                    )
                                )
                            )
                        }
                    }

                    item {
                        Button(
                            modifier = Modifier
                                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                                .height(48.dp)
                                .fillMaxWidth(),
                            onClick = onSaveClicked,
                            enabled = !state.isSending && state.habitTitle.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = KalyanTheme.colors.tintColor,
                                disabledBackgroundColor = KalyanTheme.colors.tintColor.copy(
                                    alpha = 0.3f
                                )
                            )
                        ) {
                            if (state.isSending) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(
                                    text = AppRes.string.action_add,
                                    style = KalyanTheme.typography.body,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    item {
                        Button(
                            modifier = Modifier
                                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                                .height(48.dp)
                                .fillMaxWidth(),
                            onClick = onCloseClicked,
                            enabled = !state.isSending,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = KalyanTheme.colors.controlColor,
                                disabledBackgroundColor = KalyanTheme.colors.controlColor.copy(
                                    alpha = 0.3f
                                )
                            )
                        ) {
                            Text(
                                text = AppRes.string.action_close,
                                style = KalyanTheme.typography.body,
                                color = Color.White
                            )
                        }
                    }

                    state.sendingError?.let { error ->
                        item {
                            ComposeViewInitialError(error = error)
                        }
                    }
                })
        }
    }
}

//@ExperimentalFoundationApi
//@Preview
//@Composable
//fun ComposeViewInitial_Preview() {
//    MainTheme(darkTheme = true) {
//        ComposeViewInitial(
//            state = ComposeViewState.ViewStateInitial(),
//            onCheckedChange = {},
//            onTitleChanged = {},
//            onSaveClicked = {}
//        )
//    }
//}

//@ExperimentalFoundationApi
//@Preview
//@Composable
//fun ComposeViewInitialFilled_Preview() {
//    MainTheme(darkTheme = true) {
//        ComposeViewInitial(
//            state = ComposeViewState
//                .ViewStateInitial(
//                    habitTitle = "Test habbit",
//                    isGoodHabit = false,
//                    sendingError = ComposeError.SendingGeneric,
//                    isSending = true
//                ),
//            onCheckedChange = {},
//            onTitleChanged = {},
//            onSaveClicked = {}
//        )
//    }
//}