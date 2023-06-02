package navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.WindowInsetsSides.Companion
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
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
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.navigationBarsPadding
import com.moriatsushi.insetsx.safeDrawing
import ui.KalyanTheme

internal object MainFlow : Screen {

    @OptIn(ExperimentalMaterialApi::class, ExperimentalSoftwareKeyboardApi::class)
    @Composable
    override fun Content() {
        TabNavigator(RatingTab) {
            Scaffold(
                modifier = Modifier,
                content = { paddings ->
                    BottomSheetNavigator(
                        Modifier.padding()
                    ) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = KalyanTheme.colors.primaryBackground,
                        modifier = Modifier.navigationBarsPadding(),
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
