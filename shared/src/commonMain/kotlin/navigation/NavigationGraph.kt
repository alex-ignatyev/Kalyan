package navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import ui.KalyanTheme

internal object MainFlow : Screen {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        TabNavigator(RatingTab) {
            Scaffold(
                content = { paddings ->
                    BottomSheetNavigator(modifier = Modifier.padding(paddings)) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = KalyanTheme.colors.primaryBackground,
                        elevation = 24.dp
                    ) {
                        TabNavigationItem(RatingTab)
                        TabNavigationItem(SearchTab)
                        TabNavigationItem(ProfileTab)
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
