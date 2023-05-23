package screens.main.tobacco_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.kalyan.shared.strings.AppResStrings
import ktor.getBaseUrl
import screens.main.tobacco_info.TobaccoInfoEvent.ChangeAromaSlider
import screens.main.tobacco_info.TobaccoInfoEvent.ChangeSmokinessSlider
import screens.main.tobacco_info.TobaccoInfoEvent.ChangeStrengthSlider
import screens.main.tobacco_info.TobaccoInfoEvent.ChangeTasteSlider
import screens.main.tobacco_info.TobaccoInfoEvent.OnBackClick
import screens.main.tobacco_info.TobaccoInfoEvent.VoteForTobacco
import ui.KalyanTheme
import ui.components.KalyanButton
import ui.components.KalyanDivider
import ui.components.KalyanImage
import ui.components.KalyanToolbar

@Composable
internal fun TobaccoInfoView(state: TobaccoInfoState, obtainEvent: (TobaccoInfoEvent) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        KalyanToolbar(title = AppResStrings.title_tobacco_info, onBackClick = {
            obtainEvent(OnBackClick())
        })

        TobaccoInfo(state.image, state.taste, state.company, state.line, state.strengthByCompany)

        KalyanDivider(modifier = Modifier.padding(horizontal = 32.dp).padding(top = 16.dp))

        RatingInfo(
            state.strengthByUsers,
            state.smokinessByUsers,
            state.aromaByUsers,
            state.tastePowerByUsers,
            state.ratingByUsers,
            state.votes
        )

        KalyanDivider(modifier = Modifier.padding(horizontal = 32.dp).padding(top = 16.dp))

        Text(
            text = "strengthByUser: ${state.strengthByUser}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "smokinessByUser: ${state.smokinessByUser}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "aromaByUser: ${state.aromaByUser}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "tastePowerByUser: ${state.tastePowerByUser}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "ratingByUser: ${state.ratingByUser}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        KalyanDivider()

        Text(
            text = "error: ${state.error}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )


        KalyanButton(text = "Vote") {
            obtainEvent.invoke(VoteForTobacco())
        }
    }
}

@Composable
fun TobaccoInfo(image: String, taste: String, company: String, line: String, strengthByCompany: Int) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        KalyanImage(getBaseUrl() + image, modifier = Modifier.padding(8.dp).size(128.dp)) //TODO Перенести в маппинг

        Column {
            Text(
                text = "${AppResStrings.text_info_taste}: $taste",
                style = KalyanTheme.typography.body,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )

            Text(
                text = "${AppResStrings.text_info_company}: $company",
                style = KalyanTheme.typography.body,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )

            Text(
                text = "${AppResStrings.text_info_line}: $line",
                style = KalyanTheme.typography.body,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )

            Text(
                text = "${AppResStrings.text_info_strength_by_company}: $strengthByCompany",
                style = KalyanTheme.typography.body,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )
        }
    }
}

@Composable
fun RatingInfo(
    strengthByUsers: Float,
    smokinessByUsers: Float,
    aromaByUsers: Float,
    tastePowerByUsers: Float,
    ratingByUsers: Float,
    votes: Long
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Rating By Users: $ratingByUsers",
            style = KalyanTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Strength: $strengthByUsers",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f)
            )

            Text(
                text = "Smokiness: $smokinessByUsers",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Aroma: $aromaByUsers",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f)
            )

            Text(
                text = "Taste Power: $tastePowerByUsers",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f)
            )
        }

        Text(
            text = "Votes: $votes",
            style = KalyanTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )
    }
}

data class VoteBottomSheet(val state: TobaccoInfoState, val obtainEvent: (TobaccoInfoEvent) -> Unit) : Screen {

    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        Column {
            SliderWithLabel(value = state.strengthSlider, valueRange = 0f..10f, onRadiusChange =  {
                obtainEvent(ChangeStrengthSlider(it.toFloat()))
            })

            Slider(state.smokinessSlider, {
                obtainEvent(ChangeSmokinessSlider(it))
            })

            Slider(state.aromaSlider, {
                obtainEvent(ChangeAromaSlider(it))
            })

            Slider(state.tasteSlider, {
                obtainEvent(ChangeTasteSlider(it))
            })
        }
    }
}

@Composable
fun SliderWithLabel(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    labelMinWidth: Dp = 24.dp,
    onRadiusChange: (String) -> Unit
) {

    Column() {

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val offset = getSliderOffset(
                value = value,
                valueRange = valueRange,
                boxWidth = maxWidth,
                labelWidth = labelMinWidth + 8.dp // Since we use a padding of 4.dp on either sides of the SliderLabel, we need to account for this in our calculation
            )

            //SliderLabel(label = valueRange.start.toInt().toString(), minWidth = labelMinWidth)

            if (value > valueRange.start) {
                SliderLabel(
                    label = value.toInt().toString(), minWidth = labelMinWidth, modifier = Modifier
                        .padding(start = offset)
                )
            }
        }

        Slider(
            value = value, onValueChange = {
                onRadiusChange(it.toString())
            },
            valueRange = valueRange,
            modifier = Modifier.fillMaxWidth()
        )

    }
}


@Composable
fun SliderLabel(label: String, minWidth: Dp, modifier: Modifier = Modifier) {
    Text(
        label,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = modifier
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp)
            .defaultMinSize(minWidth = minWidth)
    )
}


private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp,
    labelWidth: Dp
): Dp {

    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val positionFraction = calcFraction(valueRange.start, valueRange.endInclusive, coerced)

    return (boxWidth - labelWidth) * positionFraction
}


// Calculate the 0..1 fraction that `pos` value represents between `a` and `b`
private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)