import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.mahesaiqbal.features.favorite.FavoriteScreen
import com.mahesaiqbal.features.home.HomeScreen
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator

enum class Tab {
    POPULAR, FAVORITE
}

class TabNavigator {
    var currentTab by mutableStateOf(Tab.POPULAR)
}

val LocalTabNavigator = compositionLocalOf<TabNavigator> { error("Tab navigator not provided") }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowScope.BottomTabItem(
    tab: Tab,
    pagerState: PagerState
) {
    val tabNavigator = LocalTabNavigator.current

    val isSelected by derivedStateOf { tabNavigator.currentTab == tab }
    val scope = rememberCoroutineScope()

    BottomNavigationItem(
        selected = isSelected,
        onClick = {
            val page = when (tab) {
                Tab.POPULAR -> 0
                Tab.FAVORITE -> 1
            }
            scope.launch {
                pagerState.animateScrollToPage(page)
            }
        },
        icon = {},
        label = {
            Text(
                text = tab.name
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(
    navigator: Navigator
) {
    val pagerState = rememberPagerState { 2 }
    val tabNavigator = LocalTabNavigator.current

    when (pagerState.currentPage) {
        0 -> {
            tabNavigator.currentTab = Tab.POPULAR
        }
        1 -> {
            tabNavigator.currentTab = Tab.FAVORITE
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                contentColor = Color.Black
            ) {
                BottomTabItem(Tab.POPULAR, pagerState)
                BottomTabItem(Tab.FAVORITE, pagerState)
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = 2
        ) { index ->
            when (index) {
                0 -> {
                    HomeScreen(
                        onMovieClick = { navigator.navigate("/detail/${it.id}") }
                    )
                }
                1 -> {
                    FavoriteScreen(
                        onMovieClick = {}
                    )
                }
            }
        }
    }
}