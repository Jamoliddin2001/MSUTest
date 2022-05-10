package jamoliddin.tj.mytest_teachers.presentation.navigation.graphs

import androidx.navigation.*
import androidx.navigation.compose.composable
import jamoliddin.tj.mytest_teachers.domain.model.Graph
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.domain.util.SUBJECT_NAME
import jamoliddin.tj.mytest_teachers.presentation.screens.main.AddMyTestScreen
import jamoliddin.tj.mytest_teachers.presentation.screens.main.MainScreen
import jamoliddin.tj.mytest_teachers.presentation.screens.main.ShowMyTestScreen


fun NavGraphBuilder.MainGraph(navController: NavController) {
    navigation(
        startDestination = Screen.MainScreen.route,
        route = Graph.Main.route
    ) {
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.AddMyTestScreen.route
        ) {
            AddMyTestScreen(navController = navController)
        }
        composable(
            route = Screen.ShowMyTestScreen.route + "/{$SUBJECT_NAME}",
            arguments = listOf(navArgument(SUBJECT_NAME) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            ShowMyTestScreen(
                navController = navController,
                subject = backStackEntry.arguments?.getString(SUBJECT_NAME)
            )
        }
    }
}