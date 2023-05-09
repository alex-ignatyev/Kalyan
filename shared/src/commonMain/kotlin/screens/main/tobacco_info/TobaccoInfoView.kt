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
import ui.components.KalyanToolbar

@Composable
internal fun TobaccoInfoView(state: TobaccoInfoState, obtainEvent: (TobaccoInfoEvent) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        KalyanToolbar(title = "Tobacco Name", onBackClick = {

        })

        Text(text = state.data,
            color = KalyanTheme.colors.secondaryText,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight())

        Text(
            text = state.error,
            color = KalyanTheme.colors.errorColor,
            modifier = Modifier.padding(top = 16.dp).wrapContentHeight()
        )

        KalyanButton(text = "Тест") {
            obtainEvent.invoke(VoteForTobacco())
        }
    }
}
