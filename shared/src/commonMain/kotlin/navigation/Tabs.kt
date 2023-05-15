package navigation

import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.kalyan.shared.AppRes
import screens.main.profile.ProfileScreen
import screens.main.rating.RatingScreen
import screens.main.search.SearchScreen

internal object RatingTab : Tab {
    @Composable
    override fun Content() {
        Navigator(RatingScreen)
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Filled.Star)

            return remember {
                TabOptions(
                    index = 0u,
                    title = AppRes.string.title_rating,
                    icon = icon
                )
            }
        }
}

internal object SearchTab : Tab {
    @Composable
    override fun Content() {
        Navigator(SearchScreen)
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Filled.Search)

            return remember {
                TabOptions(
                    index = 1u,
                    title = AppRes.string.title_search,
                    icon = icon
                )
            }
        }
}

internal object ProfileTab : Tab {
    @Composable
    override fun Content() {
        Navigator(ProfileScreen)
    }

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Filled.Person)

            return remember {
                TabOptions(
                    index = 2u,
                    title = AppRes.string.title_profile,
                    icon = icon
                )
            }
        }
}
