package jamoliddin.tj.mytest.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import jamoliddin.tj.mytest.domain.model.Graph
import jamoliddin.tj.mytest.domain.model.Screen

fun NavGraphBuilder.MyTestsGraph(navController: NavController) {
    navigation(
        startDestination = Screen.MyTestsScreen.route,
        route = Graph.MyTests.route
    ){
        composable(
            route = Screen.MyTestsScreen.route
        ){

        }
    }
}