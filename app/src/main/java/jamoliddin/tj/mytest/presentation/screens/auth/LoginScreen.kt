package jamoliddin.tj.mytest.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Login")
    }
}