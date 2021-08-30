package com.tana.fashionappcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.tana.fashionappcompose.components.Screens

@Composable
fun FashionAppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {
        composable(Screens.MainScreen.route) {

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