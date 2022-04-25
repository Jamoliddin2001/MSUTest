package jamoliddin.tj.mytest.presentation.components.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import jamoliddin.tj.mytest.R
import jamoliddin.tj.mytest.domain.model.Screen
import jamoliddin.tj.mytest.presentation.theme.GrayLabel
import jamoliddin.tj.mytest.presentation.theme.Primary

@Composable
fun BottomAppBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.White
    ) {

        BottomNavigationItem(
            navController = navController,
            screen = Screen.MainScreen,
            painterId = R.drawable.ic_home,
            label = "Главная"
        )

        BottomNavigationItem(
            navController = navController,
            screen = Screen.MyTestsScreen,
            painterId = R.drawable.ic_rating,
            label = "Мои тесты"
        )

        BottomNavigationItem(
            navController = navController,
            screen = Screen.ProfileScreen,
            painterId = R.drawable.ic_profile,
            label = "Профиль"
        )


    }
}


@Composable
private fun RowScope.BottomNavigationItem(
    navController: NavController,
    screen: Screen,
    @DrawableRes painterId: Int,
    label: String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigationItem(
        currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navigateToScreen(screen, navController, currentDestination)
        },
        icon = {
            Icon(
                modifier = Modifier.size(26.dp),
                painter = painterResource(id = painterId),
                contentDescription = null
            )
        },
        label = {
            Text(
                text = label,
                fontSize = 11.sp
            )
        },
        selectedContentColor = Primary,
        unselectedContentColor = GrayLabel
    )
}

private fun navigateToScreen(
    screen: Screen,
    navController: NavController,
    currentDestination: NavDestination?
) {
    if (screen.route == "MainScreen") {
        navController.navigate("MainScreen") {
            popUpTo("MainScreen") {
                inclusive = true
            }
        }
    } else {
        navController.navigate(route = screen.route) {
            currentDestination?.route?.let {
                popUpTo(it) {
                    if (it != "MainScreen") inclusive = true
                }
            }
            launchSingleTop = true
        }
    }
}