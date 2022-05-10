package jamoliddin.tj.mytest_teachers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.presentation.components.common.BottomAppBar
import jamoliddin.tj.mytest_teachers.presentation.navigation.Navigation
import jamoliddin.tj.mytest_teachers.presentation.theme.MyTestTheme

val screensWithBottomNavigation = listOf(
    Screen.MainScreen.route,
    Screen.MyResultsScreen.route,
    Screen.ProfileScreen.route
)


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentRoute = getCurrentRoute(navController)
            MyTestTheme {
                Scaffold(
                    bottomBar = {
                    if (screensWithBottomNavigation.contains(currentRoute))
                        BottomAppBar(navController) }
                ) {
                    Navigation(
                        modifier = Modifier,
                        navController = navController
                    )
                }
            }
        }
    }
}


@Composable
private fun getCurrentRoute(navController: NavHostController) =
    runCatching {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        navBackStackEntry?.destination?.route
    }.getOrNull()