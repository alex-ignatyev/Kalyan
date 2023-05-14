package screens.main.tobacco_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import screens.main.tobacco_info.TobaccoInfoEvent.VoteForTobacco
import ui.KalyanTheme
import ui.components.KalyanButton
import ui.components.KalyanDivider
import ui.components.KalyanToolbar

@Composable
internal fun TobaccoInfoView(state: TobaccoInfoState, obtainEvent: (TobaccoInfoEvent) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        KalyanToolbar(title = "Tobacco Name", onBackClick = {

        })

        Text(
            text = "id: ${state.id}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "taste: ${state.taste}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "company: ${state.company}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "line: ${state.line}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "image: ${state.image}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "strengthByCompany: ${state.strengthByCompany}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        KalyanDivider()

        Text(
            text = "strengthByUsers: ${state.strengthByUsers}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "smokinessByUsers: ${state.smokinessByUsers}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "aromaByUsers: ${state.aromaByUsers}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "ratingByUsers: ${state.ratingByUsers}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "tastePowerByUsers: ${state.tastePowerByUsers}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        KalyanDivider()

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
            text = "votes: ${state.votes}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        Text(
            text = "error: ${state.error}",
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )


        KalyanButton(text = "Тест") {
            obtainEvent.invoke(VoteForTobacco())
        }
    }
}
