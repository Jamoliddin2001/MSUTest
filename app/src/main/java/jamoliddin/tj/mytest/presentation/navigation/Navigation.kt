package jamoliddin.tj.mytest.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.animation.AnimatedNavHost
import jamoliddin.tj.mytest.App
import jamoliddin.tj.mytest.domain.model.Graph
import jamoliddin.tj.mytest.domain.model.Screen
import jamoliddin.tj.mytest.presentation.navigation.graphs.*
import jamoliddin.tj.mytest.presentation.navigation.graphs.WelcomeGraph


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