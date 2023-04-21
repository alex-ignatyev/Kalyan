package screens.old.stats.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.themes.KalyanTheme

data class StatisticCellModel(
    val title: String,
    val activeDayList: List<Boolean>,
    val duration: String,
    val fact: String,
    val percentage: Float,
    val isPeriodic: Boolean
)

@Composable
internal fun StatisticCell(model: StatisticCellModel) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = KalyanTheme.shapes.padding,
                vertical = KalyanTheme.shapes.padding / 2
            )
            .fillMaxWidth(),
        elevation = 8.dp,
        backgroundColor = KalyanTheme.colors.primaryBackground,
        shape = KalyanTheme.shapes.cornersStyle
    ) {
        Column {
            Divider(
                modifier = Modifier.fillMaxWidth(model.percentage).height(2.dp)
                    .background(KalyanTheme.colors.tintColor)
            )

            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f).padding(top = 8.dp),
                    text = model.title,
                    style = KalyanTheme.typography.body,
                    color = KalyanTheme.colors.primaryText
                )

                val countText = if (!model.isPeriodic) model.duration else
                    "${model.fact} / ${model.duration}"

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = countText,
                    style = KalyanTheme.typography.body,
                    color = KalyanTheme.colors.secondaryText
                )
            }
        }
    }
}