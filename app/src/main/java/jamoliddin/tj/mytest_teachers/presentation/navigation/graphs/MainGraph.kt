package jamoliddin.tj.mytest_teachers.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import jamoliddin.tj.mytest_teachers.domain.model.Graph
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.presentation.screens.main.AddMyTestScreen
import jamoliddin.tj.mytest_teachers.presentation.screens.main.MainScreen


fun NavGraphBuilder.MainGraph(navController: NavController){
    navigation(
        startDestination = Screen.MainScreen.route,
        route = Graph.Main.route
    ){
        composable(
            route = Screen.MainScreen.route
        ){
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.AddMyTestScreen.route
        ){
            AddMyTestScreen(navController = navController)
        }
    }
}