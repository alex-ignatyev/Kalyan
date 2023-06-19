package screens.main.tobacco.tobacco_feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kalyan.shared.strings.AppResStrings
import com.moriatsushi.insetsx.navigationBars
import com.moriatsushi.insetsx.statusBars
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.OnTobaccoClick
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.OnTobaccoSearch
import screens.main.tobacco.tobacco_feed.TobaccoFeedEvent.RefreshTobaccoFeedScreen
import screens.main.tobacco.tobacco_feed.view.TobaccoView
import ui.KalyanTheme
import ui.components.KalyanCircularProgress
import ui.components.KalyanSearch
import utils.mvi.Event

//TODO Добавить иконки на пустой и ошибки экраны
//TODO Добавить кнопку и переводы для текстов пустыого экрана
//TODO Добавить стейты через вен разного отображаения экранов и пусть этим управляет vm

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TobaccoFeedView(state: TobaccoFeedState, obtainEvent: (Event) -> Unit) {
    val refreshing by remember { mutableStateOf(state.isRefresh) }
    val refresh = rememberPullRefreshState(refreshing, {
        obtainEvent(RefreshTobaccoFeedScreen())
    })

    Scaffold(
        modifier = Modifier.pullRefresh(refresh)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .pullRefresh(refresh),
    ) {
        Box(Modifier.background(KalyanTheme.colors.background).fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            ) {
                KalyanSearch(
                    modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
                    value = state.search,
                    onValueChange = {
                        obtainEvent(OnTobaccoSearch(it))
                    }
                )

                if (state.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        KalyanCircularProgress(color = KalyanTheme.colors.primary)
                    }
                } else {
                    if (state.data.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column {
                                Text(text = AppResStrings.text_empty_list)
                                Text(text = AppResStrings.text_cant_find)
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                            contentPadding = PaddingValues(all = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            itemsIndexed(state.data) { index, item ->
                                TobaccoView(item, index + 1, Modifier.clickable {
                                    obtainEvent.invoke(OnTobaccoClick(item.id))
                                })
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(refreshing, refresh, Modifier.align(Alignment.TopCenter))
        }
    }
}
