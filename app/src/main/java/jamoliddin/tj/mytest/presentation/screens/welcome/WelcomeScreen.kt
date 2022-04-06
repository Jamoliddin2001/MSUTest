package jamoliddin.tj.mytest.presentation.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import jamoliddin.tj.mytest.domain.model.Screen

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .clickable {
                navController.navigate(Screen.LoginScreen.route)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Welcome")
    }
}