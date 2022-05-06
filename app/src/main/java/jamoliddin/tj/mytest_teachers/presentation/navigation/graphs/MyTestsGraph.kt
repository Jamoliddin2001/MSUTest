package jamoliddin.tj.mytest_teachers.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import jamoliddin.tj.mytest_teachers.domain.model.Graph
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.presentation.screens.mytests.MyTestsScreen

fun NavGraphBuilder.MyTestsGraph(navController: NavController) {
    navigation(
        startDestination = Screen.MyTestsScreen.route,
        route = Graph.MyTests.route
    ){
        composable(
            route = Screen.MyTestsScreen.route
        ){
            MyTestsScreen(navController = navController)
        }
    }
}