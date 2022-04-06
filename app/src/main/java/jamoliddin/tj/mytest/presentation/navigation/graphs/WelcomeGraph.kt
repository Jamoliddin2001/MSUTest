package jamoliddin.tj.mytest.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import jamoliddin.tj.mytest.domain.model.Graph
import jamoliddin.tj.mytest.domain.model.Screen
import jamoliddin.tj.mytest.presentation.components.AnimationBetweenComposable
import jamoliddin.tj.mytest.presentation.screens.welcome.SplashScreen
import jamoliddin.tj.mytest.presentation.screens.welcome.WelcomeScreen


internal fun NavGraphBuilder.WelcomeGraph(navController: NavController) {
    navigation(
        startDestination = Screen.SplashScreen.route,
        route = Graph.Welcome.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.WelcomeScreen.route) {
                WelcomeScreen(navController = navController)
        }
    }
}