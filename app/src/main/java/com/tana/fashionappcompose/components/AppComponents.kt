package com.tana.fashionappcompose.components


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.tana.fashionappcompose.screens.tabscreens.NewModelScreen
import com.tana.fashionappcompose.screens.tabscreens.RecommendedScreen
import com.tana.fashionappcompose.screens.tabscreens.ShowsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object DetailScreen : Screens("detail_screen")
}


sealed class TabItems(var name: String, var screen: @Composable () -> Unit) {
    object Recommended : TabItems(name = "Recommended", screen = { RecommendedScreen() })
    object NewModel : TabItems(name = "New Models", screen = { NewModelScreen() })
    object Shows : TabItems(name = "2021 Show", screen = { ShowsScreen() })
}

@OptIn(ExperimentalPagerApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun AppTabs(
    pages: List<TabItems>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        indicator = { TabRowDefaults.Indicator(color = Color.Transparent) },
        divider = { Divider(color = Color.Transparent) }
    ) {
        pages.forEachIndexed { index, tabItem ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = tabItem.name,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(
    pages: List<TabItems>,
    pagerState: PagerState
) {
    HorizontalPager(state = pagerState) { page ->
        pages[page].screen()
    }
}
