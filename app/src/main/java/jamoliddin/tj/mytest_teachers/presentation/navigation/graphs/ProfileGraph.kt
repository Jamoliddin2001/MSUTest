package jamoliddin.tj.mytest_teachers.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import jamoliddin.tj.mytest_teachers.domain.model.Graph
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.presentation.screens.profile.ProfileScreen

fun NavGraphBuilder.ProfileGraph(navController: NavController) {
    navigation(
        startDestination = Screen.ProfileScreen.route,
        route = Graph.Profile.route
    ){
        composable(
            route = Screen.ProfileScreen.route
        ){
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screen.PersonalDataScreen.route
        ){

        }
        composable(
            route = Screen.InfoScreen.route
        ){

        }
    }
}