package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.KalyanTheme

@Composable
internal fun JetMenu(
    title: String,
    value: String,
    onMenuClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(KalyanTheme.colors.primaryBackground)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .clickable { onMenuClicked.invoke() }
                .background(KalyanTheme.colors.primaryBackground),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = title,
                style = KalyanTheme.typography.body,
                color = KalyanTheme.colors.primaryText
            )

            Text(
                text = value,
                style = KalyanTheme.typography.body,
                color = KalyanTheme.colors.secondaryText
            )
        }

        Divider(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.BottomStart),
            thickness = 0.5.dp,
            color = KalyanTheme.colors.secondaryText.copy(
                alpha = 0.3f
            )
        )
    }

}

/*
JetMenu(
title = AppRes.string.title_end_date,
value = viewState.endDate
) {
    val configuration = ModalSheetConfiguration(
        maxHeight = 0.6f,
        cornerRadius = 16,
    )
    modalController.present(configuration) { key ->
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(KalyanTheme.colors.primaryBackground)
                .padding(16.dp)
        ) {
            CCalendar(
                selectedDate = viewState.end ?: DateTime.now(),
                textColor = KalyanTheme.colors.primaryText,
                dayOfWeekColor = KalyanTheme.colors.controlColor,
                selectedColor = KalyanTheme.colors.tintColor
            ) {
                eventHandler.invoke(DetailEvent.EndDateSelected(it))
                modalController.popBackStack(key)
            }
        }
    }
}
*/

/*
DetailAction.DateError -> {
                val configuration = AlertConfiguration(
                    cornerRadius = 4, maxHeight = 0.4f,
                    maxWidth = 0.7f, alpha = 0.5f
                )
                modalController.present(alertConfiguration = configuration) { key ->
                    Column(
                        modifier = Modifier
                            .clickable { modalController.popBackStack(key) }
                            .fillMaxSize()
                            .background(KalyanTheme.colors.primaryBackground)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = AppRes.string.title_error,
                            color = KalyanTheme.colors.primaryText,
                            style = KalyanTheme.typography.header
                        )

                        Text(
                            modifier = Modifier.padding(top = 24.dp),
                            text = AppRes.string.error_date,
                            color = KalyanTheme.colors.primaryText,
                            style = KalyanTheme.typography.body
                        )
                    }
                }

                viewModel.obtainEvent(DetailEvent.ActionInvoked)
            }
*/
