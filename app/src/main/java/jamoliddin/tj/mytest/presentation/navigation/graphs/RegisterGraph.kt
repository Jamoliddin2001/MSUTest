package jamoliddin.tj.mytest.presentation.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import jamoliddin.tj.mytest.domain.model.Graph
import jamoliddin.tj.mytest.domain.model.Screen
import jamoliddin.tj.mytest.domain.util.USER_EMAIL
import jamoliddin.tj.mytest.presentation.screens.auth.register.RegisterScreen
import jamoliddin.tj.mytest.presentation.screens.auth.register.RegisterScreenPersonalData

fun NavGraphBuilder.RegisterGraph(navController: NavController) {
    navigation(
        startDestination = Screen.RegisterScreen.route,
        route = Graph.Register.route
    ) {
        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(navController = navController)
        }
        composable(
            route = Screen.RegisterScreenPersonalData.route+"/{$USER_EMAIL}",
            arguments = listOf(navArgument(USER_EMAIL) { type = NavType.StringType })
        ) { backStackEntry ->
            RegisterScreenPersonalData(
                navController = navController,
                userEmail = backStackEntry.arguments?.getString(
                    USER_EMAIL
                )
            )
        }
    }
}