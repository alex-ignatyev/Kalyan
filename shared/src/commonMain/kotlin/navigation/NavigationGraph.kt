package navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.moriatsushi.insetsx.navigationBars
import di.LocalPlatform
import di.Platform.Android
import ui.KalyanTheme
import ui.components.KalyanDivider

internal object MainFlow : Screen {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val platform = LocalPlatform.current

        TabNavigator(RatingTab) {
            Scaffold(
                modifier = Modifier,
                content = { paddings ->
                    BottomSheetNavigator() {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    if (platform == Android) {
                        AndroidBottomNavigation(
                            modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
                        ) {
                            TabNavigationItem(RatingTab)
                            TabNavigationItem(SearchTab)
                            TabNavigationItem(ProfileTab)
                        }
                    } else {
                        IosBottomNavigation() {
                            TabNavigationItem(RatingTab)
                            TabNavigationItem(SearchTab)
                            TabNavigationItem(ProfileTab)
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = tab.options.title) } },
        selectedContentColor = KalyanTheme.colors.generalColor,
        unselectedContentColor = KalyanTheme.colors.secondaryText
    )
}

@Composable
fun AndroidBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = KalyanTheme.colors.primaryBackground,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        KalyanDivider()
        Row(
            Modifier.fillMaxWidth()
                .height(56.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

//TODO Есть проблема с отображением BottomSheet тонкая полосочка над Divider
@Composable
fun IosBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = KalyanTheme.colors.primaryBackground,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        Column {
            KalyanDivider()
            Row(
                Modifier.fillMaxWidth()
                    .height(76.dp)
                    .selectableGroup(),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = content
            )
        }
    }
}
