package jamoliddin.tj.mytest.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import jamoliddin.tj.mytest.domain.model.Graph
import jamoliddin.tj.mytest.domain.model.Screen

fun NavGraphBuilder.ProfileGraph(navController: NavController) {
    navigation(
        startDestination = Screen.ProfileScreen.route,
        route = Graph.Profile.route
    ){
        composable(
            route = Screen.ProfileScreen.route
        ){

        }
    }
}