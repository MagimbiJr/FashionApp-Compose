package com.tana.fashionappcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.tana.fashionappcompose.components.Screens
import com.tana.fashionappcompose.components.TabItems
import com.tana.fashionappcompose.screens.home.HomeScreen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FashionAppNavGraph(
    navController: NavHostController,
    pagerState: PagerState,
    pages: List<TabItems>,
    coroutineScope: CoroutineScope
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {
        composable(Screens.MainScreen.route) {
            HomeScreen(
                navController = navController,
                pagerState = pagerState,
                pages = pages,
                coroutineScope = coroutineScope
            )
        }
        composable(
            "${Screens.DetailScreen.route}/{name}/{city}/{photo}/{bio}",
            arguments = listOf(
                navArgument(name = "name") { type = NavType.StringType },
                navArgument(name = "city") { type = NavType.StringType },
                navArgument(name = "photo") { type = NavType.StringType },
                navArgument(name = "bio") { type = NavType.StringType}
            )
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name")
            val city = navBackStackEntry.arguments?.getString("city")
            val photo = navBackStackEntry.arguments?.getString("photo")
            val bio = navBackStackEntry.arguments?.getString("bio")


        }
    }
}