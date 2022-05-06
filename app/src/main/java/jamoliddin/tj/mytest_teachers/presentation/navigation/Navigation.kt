package jamoliddin.tj.mytest_teachers.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import jamoliddin.tj.mytest_teachers.domain.model.Graph
import jamoliddin.tj.mytest_teachers.presentation.navigation.graphs.*
import jamoliddin.tj.mytest_teachers.presentation.navigation.graphs.WelcomeGraph


@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun Navigation(modifier: Modifier, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Graph.Welcome.route,
        modifier = modifier
    ){
        WelcomeGraph(navController = navController)
        LoginGraph(navController = navController)
        RegisterGraph(navController = navController)
        MainGraph(navController = navController)
        ProfileGraph(navController = navController)
        MyTestsGraph(navController = navController)
    }


}