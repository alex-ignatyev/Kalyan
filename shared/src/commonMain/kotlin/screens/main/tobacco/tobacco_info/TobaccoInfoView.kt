package screens.main.tobacco.tobacco_info

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.kalyan.shared.strings.AppResStrings
import com.moriatsushi.insetsx.navigationBars
import com.moriatsushi.insetsx.statusBars
import ktor.getBaseUrl
import model.data.tobacco.TobaccoVoteRequest.VoteType
import model.data.tobacco.TobaccoVoteRequest.VoteType.Aroma
import model.data.tobacco.TobaccoVoteRequest.VoteType.Rating
import model.data.tobacco.TobaccoVoteRequest.VoteType.Smokiness
import model.data.tobacco.TobaccoVoteRequest.VoteType.Strength
import model.data.tobacco.TobaccoVoteRequest.VoteType.Taste
import screens.main.tobacco.tobacco_info.TobaccoInfoEvent.OnBackClick
import screens.main.tobacco.tobacco_info.TobaccoInfoEvent.VoteForTobacco
import screens.main.tobacco.tobacco_info.view.Rating
import ui.KalyanTheme
import ui.components.KalyanButton
import ui.components.KalyanDivider
import ui.components.KalyanImage
import ui.components.KalyanToolbar

@Composable
internal fun TobaccoInfoView(state: TobaccoInfoState, obtainEvent: (TobaccoInfoEvent) -> Unit) {

    val data = state.data

    Scaffold(
        modifier = Modifier
            .background(KalyanTheme.colors.background)
            .windowInsetsPadding(WindowInsets.statusBars),
        backgroundColor = KalyanTheme.colors.background,
        topBar = {
            KalyanToolbar(
                title = AppResStrings.title_tobacco_info,
                onBackClick = {
                    obtainEvent(OnBackClick())
                })
        }
    ) {
        Column(Modifier.fillMaxSize()) {
            TobaccoInfo(
                image = data.image,
                taste = data.taste,
                company = data.company,
                line = data.line,
                strengthByCompany = data.strength
            )

            KalyanDivider(modifier = Modifier.padding(horizontal = 32.dp).padding(top = 16.dp))

            RatingInfoUsers(
                ratingByUsers = data.ratingByUsers,
                strengthByUsers = data.strengthByUsers,
                smokinessByUsers = data.smokinessByUsers,
                aromaByUsers = data.aromaByUsers,
                tasteByUsers = data.tasteByUsers,
                votes = data.votes
            )

            KalyanDivider(modifier = Modifier.padding(horizontal = 32.dp).padding(top = 16.dp))

            RatingInfoUser(
                ratingByUser = data.ratingByUser,
                strengthByUser = data.strengthByUser,
                smokinessByUser = data.smokinessByUser,
                aromaByUser = data.aromaByUser,
                tasteByUser = data.tasteByUser,
                obtainEvent = obtainEvent
            )

            Text(
                text = "error: ${state.error}",
                color = KalyanTheme.colors.error,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )
        }
    }
}

@Composable
fun TobaccoInfo(image: String, taste: String, company: String, line: String, strengthByCompany: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        KalyanImage(getBaseUrl() + image, modifier = Modifier.padding(8.dp).size(128.dp)) //TODO Перенести в маппинг

        Column {
            Text(
                text = "${AppResStrings.text_taste}: $taste",
                style = KalyanTheme.typography.body,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )

            Text(
                text = "${AppResStrings.text_company}: $company",
                style = KalyanTheme.typography.body,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )

            Text(
                text = "${AppResStrings.text_line}: $line",
                style = KalyanTheme.typography.body,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )

            Text(
                text = "${AppResStrings.text_strength}: $strengthByCompany",
                style = KalyanTheme.typography.body,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )
        }
    }
}

@Composable
fun RatingInfoUsers(
    ratingByUsers: Float,
    strengthByUsers: Float,
    smokinessByUsers: Float,
    aromaByUsers: Float,
    tasteByUsers: Float,
    votes: Long
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(
                text = "${AppResStrings.text_rating}: $ratingByUsers",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "$votes",
                style = KalyanTheme.typography.caption,
                color = KalyanTheme.colors.secondaryText,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${AppResStrings.text_strength}: $strengthByUsers",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f)
            )

            Text(
                text = "${AppResStrings.text_smokiness}: $smokinessByUsers",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${AppResStrings.text_aroma}: $aromaByUsers",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f)
            )

            Text(
                text = "${AppResStrings.text_taste}: $tasteByUsers",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f)
            )
        }
    }
}

@Composable
fun RatingInfoUser(
    ratingByUser: Long,
    strengthByUser: Long,
    smokinessByUser: Long,
    aromaByUser: Long,
    tasteByUser: Long,
    obtainEvent: (TobaccoInfoEvent) -> Unit
) {
    val bottomSheetNavigator = LocalBottomSheetNavigator.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${AppResStrings.text_rating}: $ratingByUser",
            style = KalyanTheme.typography.caption,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight().clickable {
                bottomSheetNavigator.show(VoteBottomSheet(Rating, ratingByUser, obtainEvent))
            }
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${AppResStrings.text_strength}: $strengthByUser",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f).clickable {
                    bottomSheetNavigator.show(VoteBottomSheet(Strength, strengthByUser, obtainEvent))
                }
            )

            Text(
                text = "${AppResStrings.text_smokiness}: $smokinessByUser",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f).clickable {
                    bottomSheetNavigator.show(VoteBottomSheet(Smokiness, smokinessByUser, obtainEvent))
                }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${AppResStrings.text_aroma}: $aromaByUser",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f).clickable {
                    bottomSheetNavigator.show(VoteBottomSheet(Aroma, aromaByUser, obtainEvent))
                }
            )

            Text(
                text = "${AppResStrings.text_taste}: $tasteByUser",
                style = KalyanTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp).wrapContentHeight().weight(1f).clickable {
                    bottomSheetNavigator.show(VoteBottomSheet(Taste, tasteByUser, obtainEvent))
                }
            )
        }
    }
}

data class VoteBottomSheet(val type: VoteType, val value: Long, val obtainEvent: (TobaccoInfoEvent) -> Unit) : Screen {

    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        var rate by remember { mutableStateOf(1) }

        Column(
            modifier = Modifier.windowInsetsPadding(
                WindowInsets.navigationBars.add(WindowInsets.navigationBars).add(WindowInsets(bottom = 48.dp))
            )
        ) {
            Rating(value.toInt(), modifier = Modifier.height(120.dp)) {
                rate = it
            }

            KalyanButton(text = "Vote") {
                obtainEvent(VoteForTobacco(type, rate.toLong()))
                bottomSheetNavigator.hide()
            }
        }
    }
}
