package jamoliddin.tj.mytest.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import jamoliddin.tj.mytest.domain.model.Graph
import jamoliddin.tj.mytest.domain.model.Screen
import jamoliddin.tj.mytest.presentation.components.AnimationBetweenComposable
import jamoliddin.tj.mytest.presentation.screens.auth.LoginScreen

fun NavGraphBuilder.LoginGraph(navController: NavController) {
    navigation(
        startDestination = Screen.LoginScreen.route,
        route = Graph.Login.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
    }
}